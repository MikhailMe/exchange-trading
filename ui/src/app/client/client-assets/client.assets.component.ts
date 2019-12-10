import {Component, OnInit} from '@angular/core';
import {Asset, Client} from '../../models';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.assets.component.html',
})
export class ClientAssetsComponent implements OnInit {
    private id: number;
    private client: Client;

    constructor(private router: Router,
                private clientService: ClientService,
                private storeService: StoreService) {
        this.id = this.storeService.getId();
        this.getClient();
    }

    ngOnInit() {
    }

    onAssetClicked(asset: Asset) {
        this.storeService.setPropertyId(asset.id);
        return this.router.navigateByUrl(`/client/asset/${asset.id}`);
    }

    getClient() {
        this.clientService.getById(this.id)
            .subscribe(data => this.client = data as Client, error => console.log(error));
    }
}
