import {Component, OnInit} from '@angular/core';
import {Admin} from "../../models";
import {BrokerService} from "../../services/broker.service";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
    templateUrl: './admin.brokers.component.html'
})
export class AdminBrokersComponent implements OnInit {
    protected admin: Admin;

    constructor(private brokerService: BrokerService,
                private dataService: DataService,
                private router: Router) {
        this.admin = new class implements Admin {
            brokers: null;
            credentials: null;
            id: number;
            isAuthenticated: boolean;
            name: string;
            personType: string;
            surname: string;
            transactions: null;
        };
        this.admin.brokers = dataService.getBrokers();
        /*brokerService.checkRequests(this.broker.id).subscribe(
            data => this.brokerRequests = data, error => console.log(error)
        );*/
    }

    ngOnInit() {
    }
}
