import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {BrokerageAccount, Client} from '../../models';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.brokerage.account.info.component.html',
    styleUrls: ['./client.brokerage.account.info.component.css']
})
export class ClientBrokerageAccountInfoComponent implements OnInit {
    private id: number;
    private client: Client;
    private brokerageAccount: BrokerageAccount;

    constructor(private router: Router,
                private clientService: ClientService,
                private storeService: StoreService) {
        this.id = this.storeService.getId();
        this.clientService.getById(this.id).subscribe(data => {
            this.client = data;
            this.brokerageAccount = data.brokerageAccount;
        });
    }

    ngOnInit() {
    }

    closeBrokerageAccount() {
        this.clientService.closeBrokerageAccount(this.id).subscribe(
            () => this.router.navigateByUrl('/client/base'));
    }
}
