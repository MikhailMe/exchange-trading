import {Component, OnInit} from '@angular/core';
import {Transaction} from '../../models';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.transactions.component.html'
})
export class ClientTransactionsComponent implements OnInit {
    private clientId: number;
    private transactions: Transaction[];

    constructor(private router: Router,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.clientId = this.storeService.getId();
        clientService.getTransactions(this.clientId).subscribe(data => this.transactions = data);
    }

    ngOnInit() {
    }

    onTransactionClicked(transaction: Transaction) {
        this.storeService.setPropertyId(transaction.id);
        return this.router.navigateByUrl(`/client/transaction/${transaction.id}`);
    }

}
