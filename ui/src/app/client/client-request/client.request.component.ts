import {Component, OnInit} from '@angular/core';
import {ClientRequest} from "../../models";

@Component({
    templateUrl: './client.request.component.html',
    styleUrls: ['./client.request.component.css']
})
export class ClientRequestComponent implements OnInit {
    protected clientRequest: ClientRequest;

    constructor() {

    }

    ngOnInit() {
    }

}
