import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';

@Component({
    templateUrl: './sign.in.component.html',
    styleUrls: ['./sign.in.component.css']
})
export class SignInComponent implements OnInit {
    public login: string;
    public password: string;
    public personType: string;
    public result: any;
    protected readonly authService: AuthService;

    constructor(authService: AuthService) {
        this.authService = authService;
    }

    ngOnInit() {
    }

    signIn() {
        if (this.login.length == 0 || this.password.length == 0 || this.personType.length == 0) {
            alert("You must fill all fields")
        }

        return this.authService.signIn(this.login, this.password, this.personType).subscribe(
            data => this.result = data, error => console.error(error));
    }
}
