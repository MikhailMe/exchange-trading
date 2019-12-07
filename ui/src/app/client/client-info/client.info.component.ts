import {Component, OnInit} from '@angular/core';
import {Client, Passport} from "../../models";

@Component({
    templateUrl: './client.info.component.html',
    styleUrls: ['./client.info.component.css']
})
export class ClientInfoComponent implements OnInit {
    protected client: Client;
    protected clientPassport: Passport;

    constructor() {
    }

    ngOnInit() {
    }
}
