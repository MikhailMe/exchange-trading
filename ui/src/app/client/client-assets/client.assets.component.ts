import {Component, OnInit} from '@angular/core';
import {Asset} from '../../models';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.assets.component.html',
})
export class ClientAssetsComponent implements OnInit {
    private clientId: number;
    private assets: Asset[];

    constructor(private router: Router,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.clientId = this.storeService.getId();
        this.clientService.getAssets(this.clientId).subscribe(data => this.assets = data);
    }

    ngOnInit() {
    }

    onAssetClicked(asset: Asset) {
        this.storeService.setPropertyId(asset.id);
        return this.router.navigateByUrl(`/client/asset/${asset.id}`);
    }
}
