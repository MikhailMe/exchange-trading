import {Component, OnInit} from '@angular/core';
import {Transaction} from "../../models";

@Component({
    templateUrl: './client.transaction.component.html'
})
export class ClientTransactionComponent implements OnInit {
    protected clientTransaction: Transaction;

    constructor() {
    }

    ngOnInit() {
    }

}
