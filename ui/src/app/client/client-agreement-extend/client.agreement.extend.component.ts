import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.agreement.extend.component.html',
    styleUrls: ['./client.agreement.extend.component.css']
})
export class ClientAgreementExtendComponent implements OnInit {
    protected validity: string;

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    extendBrokerAgreement() {
        return this.router.navigateByUrl('/client/agreement/info');
    }
}
