import {Component, OnInit} from '@angular/core';
import {Client, Stock} from "../../models";
import {Router} from "@angular/router";
import {StoreService} from "../../services/store.service";

@Component({
    templateUrl: './client.stocks.component.html'
})
export class ClientStocksComponent implements OnInit {
    protected client: Client;

    constructor(private router: Router,
                private storeService: StoreService) {
        this.client = storeService.getPerson() as Client;
    }

    ngOnInit() {
    }

    onStockClicked(stock: Stock) {
        this.storeService.setStock(stock);
        this.router.navigateByUrl(`/client/stock/${stock.id}`)
    }

}
