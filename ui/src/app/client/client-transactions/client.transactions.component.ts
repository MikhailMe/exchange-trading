import {Component, OnInit} from '@angular/core';
import {Client, Transaction} from "../../models";
import {Router} from "@angular/router";
import {StoreService} from "../../services/store.service";

@Component({
    templateUrl: './client.transactions.component.html'
})
export class ClientTransactionsComponent implements OnInit {
    protected client: Client;

    constructor(private router: Router,
                private storeService: StoreService) {
        this.client = storeService.getPerson() as Client;
    }

    ngOnInit() {
    }

    onTransactionClicked(transaction: Transaction) {
        this.storeService.setTransaction(transaction);
        this.router.navigateByUrl(`/client/transaction/${transaction.id}`)
    }

}
