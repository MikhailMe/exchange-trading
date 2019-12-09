import {Component, OnInit} from '@angular/core';
import {Broker, Credentials} from '../../models';
import {UserService} from '../../services/user.service';

@Component({
    templateUrl: './broker.info.component.html'
})
export class BrokerInfoComponent implements OnInit {
    protected broker: Broker;
    protected brokerCredentials: Credentials;

    constructor(private userService: UserService) {
        this.broker = userService.getUser() as Broker;
        this.brokerCredentials = this.broker.credentials;
    }

    ngOnInit() {
    }
}
