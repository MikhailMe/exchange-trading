import {Component, OnInit} from '@angular/core';
import {BrokerageAccount, ClientRequest} from "../../models";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.requests.component.html'
})
export class ClientRequestsComponent implements OnInit {
    protected brokerageAccount: BrokerageAccount;

    constructor(private dataService: DataService, private router: Router) {
        this.brokerageAccount = new BrokerageAccount();
        this.brokerageAccount.requests = dataService.getRequests();
    }

    ngOnInit() {
    }

    onRequestClicked(request: ClientRequest) {
        this.router.navigateByUrl('/client/request/1')
    }
}
