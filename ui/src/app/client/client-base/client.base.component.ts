import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Client} from '../../models';

@Component({
    templateUrl: './client.base.component.html',
    styleUrls: ['./client.base.component.css']
})
export class ClientBaseComponent implements OnInit {
    protected readonly authService: AuthService;

    @Input()
    protected client: Client;

    constructor(authService: AuthService) {
        this.authService = authService;
    }

    ngOnInit() {
    }

    signOut() {
        this.authService.signOut(1, '');
    }
}
