import {Component, OnInit} from '@angular/core';
import {Transaction} from '../../models';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.transaction.component.html'
})
export class ClientTransactionComponent implements OnInit {
    private readonly clientId: number;
    private readonly transactionId: number;
    protected clientTransaction: Transaction;

    constructor(private storeService: StoreService,
                private clientService: ClientService) {
        this.clientId = this.storeService.getId();
        this.transactionId = this.storeService.getPropertyId();
        this.clientService.getTransactionById(this.clientId, this.transactionId).subscribe(data => this.clientTransaction = data);
    }

    ngOnInit() {
    }

}
