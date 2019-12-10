import {Component, DoCheck, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {BrokerageAccount, Client} from '../../models';
import {Router} from "@angular/router";
import {StoreService} from "../../services/store.service";
import {ClientService} from "../../services/client.service";

@Component({
    templateUrl: './client.base.component.html',
    styleUrls: ['./client.base.component.css']
})
export class ClientBaseComponent implements OnInit, DoCheck {

    private client: Client;

    constructor(private router: Router,
                private storeService: StoreService,
                private authService: AuthService,
                private clientService: ClientService) {
        this.authService = authService;
        this.client = storeService.getPerson() as Client;
    }

    ngOnInit() {
    }

    ngDoCheck() {
        this.client = this.storeService.getPerson() as Client;
    }

    signOut() {
        this.authService
            .signOut(this.client.id, this.client.personType)
            .subscribe(data => console.log(data), error => console.log(error));
        this.router.navigateByUrl('/')
    }

    brokerageAccount() {
        if (this.client.brokerageAccount) {
            this.router.navigateByUrl('/client/brokerageAccount/info');
        } else {
            this.router.navigateByUrl('/client/brokerageAccount');
        }
    }
}
