import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.agreement.component.html',
    styleUrls: ['./client.agreement.component.css']
})
export class ClientAgreementComponent implements OnInit {

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    makeBrokerAgreement() {
        return this.router.navigateByUrl('/client/agreement/info');
    }
}
