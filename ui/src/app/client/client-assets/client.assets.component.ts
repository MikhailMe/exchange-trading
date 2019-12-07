import {Component, OnInit} from '@angular/core';
import {Asset, BrokerageAccount, Client} from "../../models";
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.assets.component.html',
})
export class ClientAssetsComponent implements OnInit {
    protected brokerageAccount: BrokerageAccount;

    constructor(private router: Router) {
    }

    ngOnInit() {
        this.brokerageAccount = new BrokerageAccount();
    }

    onAssetClicked(asset: Asset) {
        return this.router.navigateByUrl('/client/asset/1');
    }
}
