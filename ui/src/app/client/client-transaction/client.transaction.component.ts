import {Component, OnInit} from '@angular/core';
import {Transaction} from '../../models';
import {StoreService} from '../../services/store.service';

@Component({
    templateUrl: './client.transaction.component.html'
})
export class ClientTransactionComponent implements OnInit {
    protected clientTransaction: Transaction;

    constructor(private storeService: StoreService) {
    }

    ngOnInit() {
    }

}
