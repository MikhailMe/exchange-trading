import {Component, OnInit} from '@angular/core';
import {BrokerageAccount, Transaction} from "../../models";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.transactions.component.html'
})
export class ClientTransactionsComponent implements OnInit {
    protected brokerageAccount: BrokerageAccount;

    constructor(private dataService: DataService, private router: Router) {
        this.brokerageAccount = new BrokerageAccount();
        this.brokerageAccount.transactions = dataService.getTransactions();
    }

    ngOnInit() {
    }

    onTransactionClicked(transaction: Transaction) {
        this.router.navigateByUrl('/client/transaction/1')
    }

}
