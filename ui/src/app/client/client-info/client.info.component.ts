import {
    Component, DoCheck,
    OnInit
} from '@angular/core';
import {Client} from "../../models";
import {StoreService} from "../../services/store.service";

@Component({
    templateUrl: './client.info.component.html',
    styleUrls: ['./client.info.component.css']
})
export class ClientInfoComponent implements OnInit, DoCheck {
    protected client: Client;

    constructor(private userService: StoreService) {
    }

    ngOnInit() {
        this.client = this.userService.getPerson() as Client;
    }

    ngDoCheck() {
        this.client = this.userService.getPerson() as Client;
    }

}
