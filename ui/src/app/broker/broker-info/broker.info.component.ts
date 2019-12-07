import {Component, OnInit} from '@angular/core';
import {Broker, Credentials} from "../../models";

@Component({
    templateUrl: './broker.info.component.html'
})
export class BrokerInfoComponent implements OnInit {
    protected broker: Broker;
    protected brokerCredentials: Credentials;

    constructor() {
        this.broker = new class implements Broker {
            adminId: number;
            agreements: null;
            credentials: Credentials;
            id: number;
            isAuthenticated: boolean;
            name: string;
            personType: string;
            surname: string;
        };
        this.brokerCredentials = this.broker.credentials;
    }

    ngOnInit() {
    }
}
