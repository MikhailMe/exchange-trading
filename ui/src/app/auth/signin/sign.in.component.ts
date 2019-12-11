import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Person} from '../../models';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';

@Component({
    templateUrl: './sign.in.component.html',
})
export class SignInComponent implements OnInit {
    public login: string;
    public password: string;
    public personType: string;
    public person: Person;

    constructor(private router: Router,
                private authService: AuthService,
                private storeService: StoreService) {
        this.authService = authService;
    }

    ngOnInit() {
    }

    signIn() {
        if (this.login.length === 0 || this.password.length === 0 || this.personType.length === 0) {
            alert('You must fill all fields');
        }

        this.authService.signIn(this.login, this.password, this.personType).subscribe(
            data => {
                if (!data) {
                    alert(`No ${this.personType} with your credentials`);
                } else {
                    this.storeService.setId(data.id);
                    switch (this.personType) {
                        case 'client': this.router.navigateByUrl('/client/base'); break;
                        case 'broker': this.router.navigateByUrl('/broker/base'); break;
                        case 'admin': this.router.navigateByUrl('/admin/base'); break;
                    }
                }
            });
    }
}
