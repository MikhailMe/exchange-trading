import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';

@Component({
    templateUrl: './sign.up.component.html'
})
export class SignUpComponent implements OnInit {
    protected login: string;
    protected password: string;
    protected personType: string;
    protected name: string;
    protected surname: string;
    protected readonly authService: AuthService;

    constructor(authService: AuthService) {
        this.authService = authService;
    }

    ngOnInit() {
    }

    signUp() {
        return this.authService.signUp(this.login, this.password, this.personType, this.name, this.surname);
    }

}
