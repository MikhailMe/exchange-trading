import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {StoreService} from "../../services/store.service";
import {ClientService} from "../../services/client.service";
import {Client} from "../../models";

@Component({
    templateUrl: './client.put.money.component.html'
})
export class ClientPutMoneyComponent implements OnInit {

    @Input() protected money: number;
    @Input() protected currency: string;

    constructor(private router: Router,
                private storeService: StoreService,
                private clientService: ClientService) {
    }

    ngOnInit() {
    }

    putMoneyToBrokerageAccount() {
        let client = this.storeService.getPerson() as Client;
        this.clientService.putMoneyToAccount(client.id, this.money, this.currency)
            .subscribe(data => console.log(data), error => console.log(error));
        this.clientService.getById(client.id).subscribe(
            data => this.storeService.setPerson(data as Client), error => console.log(error));
        return this.router.navigateByUrl('/client/brokerageAccount/info')
    }

}
