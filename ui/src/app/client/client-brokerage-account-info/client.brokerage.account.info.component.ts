import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {BrokerageAccount, Passport} from "../../models";

@Component({
    templateUrl: './client.brokerage.account.info.component.html',
    styleUrls: ['./client.brokerage.account.info.component.css']
})
export class ClientBrokerageAccountInfoComponent implements OnInit {
    protected clientPassport: Passport;
    protected brokerageAccount: BrokerageAccount;

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    putMoneyToBrokerageAccount() {
        return this.router.navigateByUrl('/client/putMoney')
    }

    closeBrokerageAccount() {
        return this.router.navigateByUrl('/client/base');
    }
}
