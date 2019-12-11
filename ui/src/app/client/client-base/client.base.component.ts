import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Client} from '../../models';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.base.component.html',
    styleUrls: ['./client.base.component.css']
})
export class ClientBaseComponent implements OnInit {
    private id: number;
    private client: Client;

    constructor(private router: Router,
                private authService: AuthService,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.id = this.storeService.getId();
        this.clientService.getById(this.id).subscribe(data => this.client = data);
    }

    ngOnInit() {
    }

    signOut() {
        this.authService.signOut(this.client.id, this.client.personType)
            .subscribe(() => this.router.navigateByUrl('/'));
    }

    brokerageAccount() {
        if (this.client.brokerageAccount) {
            this.router.navigateByUrl('/client/brokerageAccount/info');
        } else {
            this.router.navigateByUrl('/client/brokerageAccount');
        }
    }

    agreement() {
        if (this.client.agreement) {
            this.router.navigateByUrl('/client/agreement/info');
        } else {
            this.router.navigateByUrl('/client/agreement');
        }
    }
}
