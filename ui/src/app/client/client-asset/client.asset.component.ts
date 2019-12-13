import {Component, OnInit} from '@angular/core';
import {Asset} from '../../models';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.asset.component.html'
})
export class ClientAssetComponent implements OnInit {
    private readonly clientId: number;
    private readonly assetId: number;
    private asset: Asset;

    constructor(private storeService: StoreService,
                private clientService: ClientService) {
        this.clientId = this.storeService.getId();
        this.assetId = this.storeService.getPropertyId();
        this.clientService.getById(this.clientId)
            .subscribe(data => this.asset = data.brokerageAccount.assets
                .find(asset => asset.id === this.assetId));
    }

    ngOnInit() {
    }

}
