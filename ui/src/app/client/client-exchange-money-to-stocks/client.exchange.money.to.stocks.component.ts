import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.exchange.money.to.stocks.component.html'
})
export class ClientExchangeMoneyToStocksComponent implements OnInit {
    private id: number;
    private fromType: string;
    private toType: string;
    private quantity: number;

    constructor(private router: Router,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.id = this.storeService.getId();
    }

    ngOnInit() {
    }

    sendRequest() {
        if (this.quantity && this.fromType && this.toType) {
            this.clientService.exchangeMoneyForStocks(this.id, this.quantity, this.fromType, this.toType)
                .subscribe(() => this.router.navigateByUrl('/client/requests'));
        } else {
            alert('Enter currency, stock type and quantity for exchange please');
        }
    }
}
