import {Component, OnInit} from '@angular/core';
import {Agreement, Broker, ClientRequest} from "../../models";
import {BrokerService} from "../../services/broker.service";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
    templateUrl: './broker.agreements.component.html',
    styles: ['td {' +
    'border: 2px solid #d4311e;' +
    'padding: 10px;' +
    '}' +
    'tr.data-row:hover {' +
    'background-color: lightblue;' +
    'cursor: pointer;' +
    '}' +
    'table {' +
    'border-collapse: collapse;' +
    'width: 200px;' +
    'text-align: center;' +
    '' +
    '} caption {' +
    'font-size: 25px;' +
    'font-weight: bold;' +
    'margin-bottom: 15px;' +
    '} tr th {' +
    'font-weight: bold;' +
    'font-size: 15px;' +
    'background-color: #f4511e;' +
    'padding: 10px;' +
    'border: 2px solid #d4311e;' +
    'text-align: center;' +
    '}']
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
