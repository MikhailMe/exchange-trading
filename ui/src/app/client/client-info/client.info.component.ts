import {
    Component,
    OnInit
} from '@angular/core';
import {Client} from '../../models';
import {StoreService} from '../../services/store.service';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.info.component.html',
    styleUrls: ['./client.info.component.css']
})
export class ClientInfoComponent implements OnInit {
    private id: number;
    protected client: Client;
    editMode = false;

    constructor(private storeService: StoreService,
                private clientService: ClientService) {
        this.id = this.storeService.getId();
        this.getClient();
    }

    getClient() {
        this.clientService.getById(this.id).subscribe(data => this.client = data as Client, error => console.log(error));
    }

    ngOnInit() {
    }

}
