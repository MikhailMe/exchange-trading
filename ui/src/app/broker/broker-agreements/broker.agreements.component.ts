import {Component, OnInit} from '@angular/core';
import {Agreement} from '../../models';
import {BrokerService} from '../../services/broker.service';
import {Router} from '@angular/router';
import {StoreService} from "../../services/store.service";

@Component({
    templateUrl: './broker.agreements.component.html'
})
export class BrokerAgreementsComponent implements OnInit {
    private brokerId: number;
    private brokerAgreements: Agreement[];

    constructor(private router: Router,
                private storeService: StoreService,
                private brokerService: BrokerService) {
        this.brokerId = this.storeService.getId();
        this.brokerService.getById(this.brokerId).subscribe(data => this.brokerAgreements = data.agreements);
    }

    ngOnInit() {
    }

    onAgreementClick(agreement: Agreement) {
        this.storeService.setPropertyId(agreement.id);
        return this.router.navigateByUrl(`/broker/agreement/${agreement.id}`);
    }
}
