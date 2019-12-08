import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
    templateUrl: './admin.base.component.html'
})
export class AdminBaseComponent implements OnInit {
    protected readonly authService: AuthService;

    constructor(authService: AuthService) {
        this.authService = authService;
    }

    ngOnInit() {
    }

    signOut() {
        this.authService.signOut(1, '');
    }
}
