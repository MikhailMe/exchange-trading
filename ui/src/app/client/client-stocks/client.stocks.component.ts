import {Component, OnInit} from '@angular/core';
import {BrokerageAccount, Stock, Transaction} from "../../models";
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.stocks.component.html'
})
export class ClientStocksComponent implements OnInit {
    protected brokerageAccount: BrokerageAccount;

    constructor(private router: Router) {
        this.brokerageAccount = new BrokerageAccount();
    }

    ngOnInit() {
    }

    onStockClicked(stock: Stock) {
        this.router.navigateByUrl('/client/stock/1')
    }

}
