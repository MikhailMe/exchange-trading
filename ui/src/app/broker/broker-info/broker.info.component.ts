import {Component, OnInit} from '@angular/core';
import {Broker} from '../../models';
import {StoreService} from '../../services/store.service';
import {BrokerService} from "../../services/broker.service";

@Component({
    templateUrl: './broker.info.component.html'
})
export class BrokerInfoComponent implements OnInit {
    private brokerId: number;
    private broker: Broker;

    constructor(private storeService: StoreService,
                private brokerService: BrokerService) {
        this.brokerId = this.storeService.getId();
        this.brokerService.getById(this.brokerId).subscribe(data => this.broker = data);
    }

    ngOnInit() {
    }
}
