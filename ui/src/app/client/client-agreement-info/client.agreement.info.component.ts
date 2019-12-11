import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Client} from '../../models';
import {ClientService} from '../../services/client.service';
import {StoreService} from '../../services/store.service';

@Component({
    templateUrl: './client.agreement.info.component.html',
    styleUrls: ['./client.agreement.info.component.css']
})
export class ClientAgreementInfoComponent implements OnInit {
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

    breakBrokerAgreement() {
        this.clientService.breakBrokerAgreement(this.id)
            .subscribe(() => this.router.navigateByUrl('/client/base'));
    }

    getClient() {
        this.clientService.getById(this.id)
            .subscribe(data => this.client = data as Client);
    }
}
