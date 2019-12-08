import {Component, OnInit} from '@angular/core';
import {Admin, Broker, ClientRequest} from "../../models";
import {BrokerService} from "../../services/broker.service";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
    templateUrl: './admin.requests.component.html'
})
export class AdminRequestsComponent implements OnInit {
    protected admin: Admin;
    protected adminRequests: ClientRequest[];

    constructor(private brokerService: BrokerService,
                private dataService: DataService,
                private router: Router) {
        this.adminRequests = dataService.getRequests();
        /*brokerService.checkRequests(this.broker.id).subscribe(
            data => this.brokerRequests = data, error => console.log(error)
        );*/
    }

    ngOnInit() {
    }

    onRequestClick(request: ClientRequest) {
        this.router.navigateByUrl('/admin/request/1');
    }
}
