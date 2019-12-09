import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Admin, Broker, Client, Person} from '../../models';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';

@Component({
    templateUrl: './sign.in.component.html',
})
export class SignInComponent implements OnInit {
    public login: string;
    public password: string;
    public personType: string;
    public person: Person;
    protected readonly authService: AuthService;

    constructor(private router: Router, authService: AuthService, private userService: UserService) {
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
                this.userService.setUser(data);
                switch (this.personType) {
                    case 'client': this.person = data as Client; this.router.navigateByUrl('/client/base'); break;
                    case 'broker': this.person = data as Broker; this.router.navigateByUrl('/broker/base', { state: {data: this.person}}); break;
                    case 'admin': this.person = data as Admin; this.router.navigateByUrl('/admin/base'); break;
                }
            }, error => console.error(error));
    }
}
