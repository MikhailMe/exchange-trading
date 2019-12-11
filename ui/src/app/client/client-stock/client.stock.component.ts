import {Component, OnInit} from '@angular/core';
import {Stock} from '../../models';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.stock.component.html'
})
export class ClientStockComponent implements OnInit {
    private readonly clientId: number;
    private readonly stockId: number;
    protected stock: Stock;

    constructor(private storeService: StoreService,
                private clientService: ClientService) {
        this.clientId = this.storeService.getId();
        this.stockId = this.storeService.getPropertyId();
        this.clientService.getStockById(this.clientId, this.stockId).subscribe(data => this.stock = data);
    }

    ngOnInit() {
    }

}
