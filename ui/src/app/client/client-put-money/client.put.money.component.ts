import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.put.money.component.html'
})
export class ClientPutMoneyComponent implements OnInit {

    @Input() protected money: number;
    @Input() protected currency: string;

    private id: number;

    constructor(private router: Router,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.id = this.storeService.getId();
    }

    ngOnInit() {
    }

    putMoneyToBrokerageAccount() {
        if (this.money && this.currency) {
            this.clientService.putMoneyToAccount(this.id, this.money, this.currency)
                .subscribe(() => this.router.navigateByUrl('/client/brokerageAccount/info'));
        } else {
            alert('Enter amount of money and currency please');
        }
    }

}
