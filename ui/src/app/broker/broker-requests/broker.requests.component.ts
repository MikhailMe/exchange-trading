import {Component, OnInit} from '@angular/core';
import {ClientRequest} from '../../models';
import {BrokerService} from '../../services/broker.service';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';

@Component({
    templateUrl: './broker.requests.component.html'
})
export class BrokerRequestsComponent implements OnInit {
    protected brokerId: number;
    protected brokerRequests: ClientRequest[];

    constructor(private router: Router,
                private storeService: StoreService,
                private brokerService: BrokerService) {
        this.brokerId = this.storeService.getId();
        this.brokerService.checkRequests(this.brokerId).subscribe(data => this.brokerRequests = data);
    }

    ngOnInit() {
    }

    onRequestClick(request: ClientRequest) {
        this.storeService.setPropertyId(request.id);
        return this.router.navigateByUrl(`/broker/request/${request.id}`);
    }
}
