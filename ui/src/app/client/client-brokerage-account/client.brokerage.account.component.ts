import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.brokerage.account.component.html',
    styleUrls: ['./client.brokerage.account.component.css']
})
export class ClientBrokerageAccountComponent implements OnInit {

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    openBrokerageAccount() {
        return this.router.navigateByUrl('/client/brokerageAccount/info')
    }
}
