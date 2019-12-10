import {Component, DoCheck, OnInit} from '@angular/core';
import {Asset, Client} from "../../models";
import {Router} from "@angular/router";
import {StoreService} from "../../services/store.service";

@Component({
    templateUrl: './client.assets.component.html',
})
export class ClientAssetsComponent implements OnInit, DoCheck {
    protected client: Client;

    constructor(private router: Router,
                private storeService: StoreService) {
        this.client = storeService.getPerson() as Client;
    }

    ngOnInit() {
    }

    ngDoCheck() {
        this.client = this.storeService.getPerson() as Client;
    }

    onAssetClicked(asset: Asset) {
        this.storeService.setAsset(asset);
        return this.router.navigateByUrl(`/client/asset/${asset.id}`);
    }
}
