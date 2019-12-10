import {Component, DoCheck, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Client} from "../../models";
import {StoreService} from "../../services/store.service";

@Component({
    templateUrl: './client.brokerage.account.info.component.html',
    styleUrls: ['./client.brokerage.account.info.component.css']
})
export class ClientBrokerageAccountInfoComponent implements OnInit, DoCheck {
    private client: Client;

    constructor(private router: Router,
                private storeService: StoreService) {
        this.client = this.storeService.getPerson() as Client;
    }

    ngOnInit() {
    }

    ngDoCheck() {
        this.client = this.storeService.getPerson() as Client;
        console.log(this.client);
    }
}
