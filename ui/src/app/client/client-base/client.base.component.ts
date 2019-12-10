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
    private screenType: string;

    constructor(private router: Router,
                private authService: AuthService,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.id = this.storeService.getId();
        this.getClient();
    }

    ngOnInit() {
        this.screenType = 'base';
    }

    getClient() {
        this.clientService.getById(this.id)
            .subscribe(data => this.client = data as Client);
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
}
