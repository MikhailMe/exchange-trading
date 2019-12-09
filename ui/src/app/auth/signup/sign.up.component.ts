import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Admin, Broker, Person} from '../../models';
import {Router} from '@angular/router';
import {Client} from '../../models/actors/client.model';

@Component({
    templateUrl: './sign.up.component.html',
    styleUrls: ['./sign.up.component.css']
})
export class SignUpComponent implements OnInit {
    protected login: string;
    protected password: string;
    protected password_repeat: string;
    protected personType: string;
    protected name: string;
    protected surname: string;
    protected person: Person;
    protected readonly authService: AuthService;

    constructor(private router: Router, authService: AuthService) {
        this.authService = authService;
    }

    ngOnInit() {
    }

    signUp() {
        if (this.password !== this.password_repeat) {
            alert('passwords don\'t match');
        } else {

            this.authService.signUp(this.login, this.password, this.personType, this.name, this.surname).subscribe(
                data => {
                    switch (this.personType) {
                        case 'client': this.person = data as Client; break;
                        case 'broker': this.person = data as Broker; break;
                        case 'admin': this.person = data as Admin; break;
                    }
                }, error => console.error(error)
            );
            this.router.navigateByUrl('/signin');
        }
    }

}
