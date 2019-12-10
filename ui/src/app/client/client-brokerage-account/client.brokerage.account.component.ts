import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Client} from '../../models';
import {ClientService} from '../../services/client.service';
import {StoreService} from '../../services/store.service';

@Component({
    templateUrl: './client.brokerage.account.component.html'
})
export class ClientBrokerageAccountComponent implements OnInit {
    private id: number;
    private client: Client;

    constructor(private router: Router,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.id = this.storeService.getId();
        this.getClient();
    }

    ngOnInit() {
    }

    openBrokerageAccount() {
        this.clientService.openBrokerageAccount(this.client.id)
            .subscribe(() => this.router.navigateByUrl('/client/brokerageAccount/info'));
    }

    getClient() {
        this.clientService.getById(this.id).subscribe(data => this.client = data);
    }
}
