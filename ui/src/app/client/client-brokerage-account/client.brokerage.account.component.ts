import {Component, DoCheck, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {BrokerageAccount, Client} from "../../models";
import {ClientService} from "../../services/client.service";
import {StoreService} from "../../services/store.service";

@Component({
    templateUrl: './client.brokerage.account.component.html'
})
export class ClientBrokerageAccountComponent implements OnInit, DoCheck {

    private client: Client;

    constructor(private router: Router,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.client = this.storeService.getPerson() as Client;
    }

    ngOnInit() {
    }

    ngDoCheck() {
        this.client = this.storeService.getPerson() as Client;
    }

    openBrokerageAccount() {
        this.clientService.openBrokerageAccount(this.client.id).subscribe(
            data => {
                let client = this.storeService.getPerson() as Client;
                client.brokerageAccount = data as BrokerageAccount;
                this.storeService.setPerson(client);
                console.log(this.storeService.getPerson());
            }, error => console.log(error)
        );
        this.router.navigateByUrl('/client/brokerageAccount/info');
    }
}
