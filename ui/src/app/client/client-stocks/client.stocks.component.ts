import {Component, OnInit} from '@angular/core';
import {Stock} from '../../models';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.stocks.component.html'
})
export class ClientStocksComponent implements OnInit {
    private clientId: number;
    private stocks: Stock[];

    constructor(private router: Router,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.clientId = this.storeService.getId();
        this.clientService.getStocks(this.clientId).subscribe(data => this.stocks = data);
    }

    ngOnInit() {
    }

    onStockClicked(stock: Stock) {
        this.storeService.setPropertyId(stock.id);
        return this.router.navigateByUrl(`/client/stock/${stock.id}`);
    }

}
