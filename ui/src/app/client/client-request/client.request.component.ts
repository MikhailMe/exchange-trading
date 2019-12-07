import {Component, OnInit} from '@angular/core';
import {ClientRequest} from "../../models";

@Component({
    templateUrl: './client.request.component.html'
})
export class ClientRequestComponent implements OnInit {
    protected clientRequest: ClientRequest;

    constructor() {

    }

    ngOnInit() {
    }

}
