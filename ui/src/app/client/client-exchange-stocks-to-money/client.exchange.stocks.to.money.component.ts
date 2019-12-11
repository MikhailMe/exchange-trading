import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.exchange.stocks.to.money.component.html'
})
export class ClientExchangeStocksToMoneyComponent implements OnInit {
    private clientId: number;
    private fromType: string;
    private toType: string;
    private quantity: number;

    constructor(private router: Router,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.clientId = this.storeService.getId();
    }

    ngOnInit() {
    }

    sendRequest() {
        if (this.quantity && this.fromType && this.toType) {
            this.clientService.exchangeStocksToMoney(this.clientId, this.quantity, this.fromType, this.toType)
                .subscribe(() => this.router.navigateByUrl('/client/requests'));
        } else {
            alert('Enter currency, stock type and quantity for exchange please');
        }
    }
}
