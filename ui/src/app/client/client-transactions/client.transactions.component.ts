import {Component, OnInit} from '@angular/core';
import {Client, Transaction} from "../../models";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.transactions.component.html'
})
export class ClientTransactionsComponent implements OnInit {
    protected client: Client;

    constructor(private dataService: DataService, private router: Router) {
        this.client = new class implements Client {
            agreement: null;
            brokerageAccount: null;
            credentials: null;
            id: number;
            isAuthenticated: boolean;
            name: string;
            passport: null;
            personType: string;
            requests: null;
            surname: string;
            transactions: Transaction[];
        };
        this.client.transactions = dataService.getTransactions();
    }

    ngOnInit() {
    }

    onTransactionClicked(transaction: Transaction) {
        this.router.navigateByUrl('/client/transaction/1')
    }

}
