import {Component, OnInit} from '@angular/core';
import {Broker, Credentials} from '../../models';
import {StoreService} from '../../services/store.service';

@Component({
    templateUrl: './broker.info.component.html'
})
export class BrokerInfoComponent implements OnInit {
    protected broker: Broker;
    protected brokerCredentials: Credentials;

    constructor(private userService: StoreService) {

        this.brokerCredentials = this.broker.credentials;
    }

    ngOnInit() {
    }
}
