import {Component, OnInit} from '@angular/core';
import {ClientRequest} from '../../models';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.request.component.html'
})
export class ClientRequestComponent implements OnInit {
    private readonly clientId: number;
    private readonly requestId: number;
    private clientRequest: ClientRequest;

    constructor(private storeService: StoreService,
                private clientService: ClientService) {
        this.clientId = this.storeService.getId();
        this.requestId = this.storeService.getPropertyId();
        this.clientService.getRequestById(this.clientId, this.requestId).subscribe(data => this.clientRequest = data);
    }

    ngOnInit() {
    }

}
