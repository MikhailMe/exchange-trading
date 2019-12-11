import {Component, OnInit} from '@angular/core';
import {Asset} from '../../models';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.asset.component.html'
})
export class ClientAssetComponent implements OnInit {
    private clientId: number;
    private assetId: number;
    private asset: Asset;

    constructor(private storeService: StoreService,
                private clientService: ClientService) {
        this.clientId = this.storeService.getId();
        this.assetId = this.storeService.getPropertyId();
        this.clientService.getAssetById(this.clientId, this.assetId).subscribe(data => this.asset = data);
    }

    ngOnInit() {
    }

}
