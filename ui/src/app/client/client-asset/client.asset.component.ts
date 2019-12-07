import {Component, OnInit} from '@angular/core';
import {Asset} from "../../models";

@Component({
    templateUrl: './client.asset.component.html'
})
export class ClientAssetComponent implements OnInit {
    protected asset: Asset;

    constructor() {

    }

    ngOnInit() {
    }

}
