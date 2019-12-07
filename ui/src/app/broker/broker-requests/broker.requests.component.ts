import {Component, OnInit} from '@angular/core';
import {Broker, ClientRequest} from "../../models";
import {BrokerService} from "../../services/broker.service";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
    templateUrl: './broker.requests.component.html'
})
export class BrokerRequestsComponent implements OnInit {
    protected broker: Broker;
    protected brokerRequests: ClientRequest[];

    constructor(private brokerService: BrokerService,
                private dataService: DataService,
                private router: Router) {
        this.brokerRequests = dataService.getRequests();
        /*brokerService.checkRequests(this.broker.id).subscribe(
            data => this.brokerRequests = data, error => console.log(error)
        );*/
    }

    ngOnInit() {
    }

    onRequestClick(request: ClientRequest) {
        this.router.navigateByUrl('/broker/request/1');
    }
}
