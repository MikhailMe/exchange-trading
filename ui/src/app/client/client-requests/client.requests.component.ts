import {Component, OnInit} from '@angular/core';
import {Client, ClientRequest} from "../../models";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.requests.component.html'
})
export class ClientRequestsComponent implements OnInit {
    protected client: Client;

    constructor(private dataService: DataService, private router: Router) {
        this.client = new class implements Client {
            agreement: null;
            brokerageAccount: null;
            credentials: null;
            id: number;
            isAuthenticated: boolean;
            name: string;
            passport: null;
            personType: string;
            requests: null;
            surname: string;
            transactions: null[];
        };
        this.client.requests = dataService.getRequests();
    }

    ngOnInit() {
    }

    onRequestClicked(request: ClientRequest) {
        this.router.navigateByUrl('/client/request/1')
    }
}
