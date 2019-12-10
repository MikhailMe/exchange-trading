import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {Client} from '../../models';
import {ClientService} from '../../services/client.service';

@Component({
    selector: 'client-passport',
    templateUrl: './client.passport.component.html'
})
export class ClientPassportComponent implements OnInit {
    @Input() client: Client;
    @Output() passportChanged = new EventEmitter();

    constructor(private router: Router,
                private clientService: ClientService) {
    }

    ngOnInit() {
        if (!this.client.passport) {
            this.client.passport = {series: 0, number: 0, id: undefined};
        } else {
            // tslint:disable-next-line:no-unused-expression
            !this.client.passport.series && (this.client.passport.series = 0);
            // tslint:disable-next-line:no-unused-expression
            !this.client.passport.number && (this.client.passport.number = 0);
        }
    }

    updatePassport() {
        this.clientService.setClientPassport(this.client.id, this.client.passport.series, this.client.passport.number)
            .subscribe(() => this.passportChanged.emit());
    }
}
