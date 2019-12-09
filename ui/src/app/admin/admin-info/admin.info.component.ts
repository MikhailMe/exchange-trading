import {Component, OnInit} from '@angular/core';
import {Admin, Broker, Credentials} from '../../models';

@Component({
    templateUrl: './admin.info.component.html'
})
export class AdminInfoComponent implements OnInit {
    protected admin: Admin;
    protected adminCredentials: Credentials;

    constructor() {
        this.admin = new class implements Admin {
            brokers: Broker[];
            credentials: Credentials;
            id: number;
            isAuthenticated: boolean;
            name: string;
            personType: string;
            surname: string;
            transactions: null;
        };
        this.adminCredentials = this.admin.credentials;
    }

    ngOnInit() {
    }
}
