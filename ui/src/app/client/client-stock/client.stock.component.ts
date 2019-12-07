import {Component, OnInit} from '@angular/core';
import {Stock} from "../../models";

@Component({
    templateUrl: './client.stock.component.html'
})
export class ClientStockComponent implements OnInit {
    protected stock: Stock;

    constructor() {
    }

    ngOnInit() {
    }

}
