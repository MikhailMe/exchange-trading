import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.exchange.stocks.to.money.component.html'
})
export class ClientExchangeStocksToMoneyComponent implements OnInit {
    protected fromType: string;
    protected toType: string;
    protected quantity: number;

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    sendRequest() {
        return this.router.navigateByUrl('/client/requests')
    }
}
