import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.put.money.component.html',
    styleUrls: ['./client.put.money.component.css']
})
export class ClientPutMoneyComponent implements OnInit {

    protected money: number;
    protected currency: string;

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    putMoneyToBrokerageAccount() {
        return this.router.navigateByUrl('/client/brokerageAccount/info')
    }

}
