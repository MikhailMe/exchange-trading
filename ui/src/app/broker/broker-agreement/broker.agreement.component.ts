import {Component, OnInit} from '@angular/core';
import {Agreement, Broker} from "../../models";
import {BrokerService} from "../../services/broker.service";
import {Router} from "@angular/router";

@Component({
    templateUrl: './broker.agreement.component.html'
})
export class BrokerAgreementComponent implements OnInit {
    protected broker: Broker;
    protected brokerAgreement: Agreement;

    constructor(private brokerService: BrokerService,
                private router: Router) {
    }

    ngOnInit() {
    }

}
