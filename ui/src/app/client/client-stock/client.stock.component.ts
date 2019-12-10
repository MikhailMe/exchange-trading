import {Component, OnInit} from '@angular/core';
import {Stock} from "../../models";
import {StoreService} from "../../services/store.service";

@Component({
    templateUrl: './client.stock.component.html'
})
export class ClientStockComponent implements OnInit {
    protected stock: Stock;

    constructor(private storeService: StoreService) {
        this.stock = storeService.getStock();
    }

    ngOnInit() {
    }

}
