import {Component, OnInit} from '@angular/core';
import {Asset} from "../../models";
import {StoreService} from "../../services/store.service";

@Component({
    templateUrl: './client.asset.component.html'
})
export class ClientAssetComponent implements OnInit {
    protected asset: Asset;

    constructor(private storeService: StoreService) {
        this.asset = storeService.getAsset();
    }

    ngOnInit() {
    }

}
