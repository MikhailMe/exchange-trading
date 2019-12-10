import {Component, OnInit} from '@angular/core';
import {Agreement, Broker, ClientRequest} from "../../models";
import {BrokerService} from "../../services/broker.service";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
    templateUrl: './broker.agreements.component.html'
})
export class BrokerAgreementsComponent implements OnInit {
    protected broker: Broker;
    protected brokerAgreements: Agreement[];

    constructor(private brokerService: BrokerService,
                private dataService: DataService,
                private router: Router) {
        this.brokerAgreements = dataService.getAgreements();
    }

    ngOnInit() {
    }

    onAgreementClick(agreement: Agreement) {
        this.router.navigateByUrl('/broker/agreement/1');
    }
}
