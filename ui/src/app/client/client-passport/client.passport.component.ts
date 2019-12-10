import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';
import {Client} from '../../models';
import {ClientService} from '../../services/client.service';

@Component({
    templateUrl: './client.passport.component.html'
})
export class ClientPassportComponent implements OnInit {
    protected client: Client;
    @Input() protected series: number;
    @Input() protected number: number;

    constructor(private router: Router,
                private clientService: ClientService,
                private userService: StoreService) {
        this.client = userService.getPerson() as Client;
        if (this.client.passport) {
            this.series = this.client.passport.series;
            this.number = this.client.passport.number;
        }
    }

    ngOnInit() {
    }

    updatePassport() {
        this.clientService
            .setClientPassport(this.client.id, this.series, this.number)
            .subscribe(data => this.userService.setPerson(data as Client), error => console.log(error));
        return this.router.navigateByUrl('/client/info')
    }
}
