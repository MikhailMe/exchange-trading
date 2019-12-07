import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Agreement} from "../../models";

@Component({
    templateUrl: './client.agreement.info.component.html',
    styleUrls: ['./client.agreement.info.component.css']
})
export class ClientAgreementInfoComponent implements OnInit {
    protected clientAgreement: Agreement;

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    breakBrokerAgreement() {
        return this.router.navigateByUrl('/client/base');
    }

    extendBrokerAgreement() {
        return this.router.navigateByUrl('/client/agreement/extend');
    }
}
