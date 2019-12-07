import {Component, OnInit} from '@angular/core';
import {ClientRequest} from "../../models";
import {Router} from "@angular/router";

@Component({
    templateUrl: './broker.request.component.html'
})
export class BrokerRequestComponent implements OnInit {
    protected clientRequest: ClientRequest;

    constructor(private router: Router) {

    }

    ngOnInit() {
    }

    approve() {
        this.router.navigateByUrl('/broker/requests');
    }

    decline() {
        this.router.navigateByUrl('/broker/requests');
    }
}
