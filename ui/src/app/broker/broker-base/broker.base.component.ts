import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute} from '@angular/router';
import {Broker} from '../../models';
import {StoreService} from '../../services/store.service';

@Component({
    templateUrl: './broker.base.component.html'
})
export class BrokerBaseComponent implements OnInit {
    protected readonly authService: AuthService;
    protected broker: Broker;

    constructor(authService: AuthService,
                public activatedRoute: ActivatedRoute,
                private userService: StoreService) {
        this.authService = authService;
    }

    ngOnInit() {
        this.broker = this.userService.getPerson() as Broker;
    }

    signOut() {
        console.log(this.broker);
        this.authService.signOut(1, '');
    }
}
