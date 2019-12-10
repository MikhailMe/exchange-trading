import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Client} from '../../models';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.brokerage.account.info.component.html',
    styleUrls: ['./client.brokerage.account.info.component.css']
})
export class ClientBrokerageAccountInfoComponent implements OnInit {
    private id: number;
    private client: Client;

    constructor(private router: Router,
                private clientService: ClientService,
                private storeService: StoreService) {
        this.id = this.storeService.getId();
        this.getClient();
    }

    ngOnInit() {
    }

    getClient() {
        this.clientService.getById(this.id).subscribe(data => this.client = data);
    }

    closeBrokerageAccount() {
        this.clientService.closeBrokerageAccount(this.client.id).subscribe(
            () => this.router.navigateByUrl('/client/base'));
    }
}
