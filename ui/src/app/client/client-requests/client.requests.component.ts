import {Component, OnInit} from '@angular/core';
import {ClientRequest} from '../../models';
import {Router} from '@angular/router';
import {ClientService} from '../../services/client.service';
import {StoreService} from '../../services/store.service';

@Component({
    templateUrl: './client.requests.component.html'
})
export class ClientRequestsComponent implements OnInit {
    private clientId: number;
    private requests: ClientRequest[];

    constructor(private router: Router,
                private storeService: StoreService,
                private clientService: ClientService) {
        this.clientId = this.storeService.getId();
        clientService.getRequests(this.clientId).subscribe(data => this.requests = data);
    }

    ngOnInit() {
    }

    onRequestClicked(request: ClientRequest) {
        this.storeService.setPropertyId(request.id);
        return this.router.navigateByUrl(`/client/request/${request.id}`);
    }

}
