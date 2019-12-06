import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';

@Component({
    templateUrl: './sign.in.component.html'
})
export class SignInComponent implements OnInit {
    public login = '';
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
        return this.authService.signIn(this.login, this.password, this.personType).subscribe(
            data => this.result = data, error => console.error(error));
    }
}
