import {Component, OnInit} from '@angular/core';
import {Asset, Client} from '../../models';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.asset.component.html'
})
export class ClientAssetComponent implements OnInit {
    protected asset: Asset;

    constructor(private clientService: ClientService,
                private storeService: StoreService) {
        const clientId = this.storeService.getId();
        const assetId = this.storeService.getPropertyId();

        this.clientService.getById(clientId)
            .subscribe(data => {
                const client = data as Client;
                this.asset = client.brokerageAccount.assets.find(x => x.id === assetId);
            });
    }

    ngOnInit() {
    }

}
