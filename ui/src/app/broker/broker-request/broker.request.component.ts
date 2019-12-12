import {Component, OnInit} from '@angular/core';
import {ClientRequest} from '../../models';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {BrokerService} from '../../services/broker.service';

@Component({
    templateUrl: './broker.request.component.html',
    styleUrls: ['./broker.request.component.css']
})
export class BrokerRequestComponent implements OnInit {
    private isValid = 0;
    private isValidated = false;
    private readonly brokerId: number;
    private readonly clientRequestId: number;
    private clientRequest: ClientRequest;

    constructor(private router: Router,
                private storeService: StoreService,
                private brokerService: BrokerService) {
        this.brokerId = this.storeService.getId();
        this.clientRequestId = this.storeService.getPropertyId();
        this.brokerService.checkRequests(this.brokerId)
            .subscribe(data => this.clientRequest = data.find(x => x.id === this.clientRequestId));
    }

    ngOnInit() {
    }

    validate() {
        this.brokerService.validateClientRequest(this.brokerId, this.clientRequestId)
            .subscribe(data => {
                if (data) this.isValid = 1;
                else this.isValid = 2;
            });
        this.isValidated = true;
    }

    approve() {
        this.brokerService.approveClientRequest(this.brokerId, this.clientRequestId)
            .subscribe(() => this.router.navigateByUrl('/broker/requests'),
                error => console.log(error));
    }

    decline() {
        this.brokerService.declineClientRequest(this.brokerId, this.clientRequestId)
            .subscribe(() => this.router.navigateByUrl('/broker/requests'),
                error => console.log(error));
    }
}
