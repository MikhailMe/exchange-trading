import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ClientService} from '../../services/client.service';
import {StoreService} from '../../services/store.service';

@Component({
    templateUrl: './client.agreement.extend.component.html'
})
export class ClientAgreementExtendComponent implements OnInit {
    private id: number;
    private validity: string;

    constructor(private router: Router,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.id = this.storeService.getId();
    }

    ngOnInit() {
    }

    extendBrokerAgreement() {
        this.clientService.extendBrokerAgreement(this.id, this.validity)
            .subscribe(() => this.router.navigateByUrl('/client/agreement/info'));
    }
}
