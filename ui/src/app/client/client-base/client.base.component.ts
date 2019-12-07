import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
    templateUrl: './client.base.component.html',
    styleUrls: ['./client.base.component.css']
})
export class ClientBaseComponent implements OnInit {
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
