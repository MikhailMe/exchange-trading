import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {Client} from '../models';
import {ClientService} from '../services';

@Component({
    selector: 'app-settings-page',
    templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {
    user: Client = {} as Client;

    constructor(
        private router: Router,
        private userService: ClientService,
    ) {

    }

    ngOnInit() {

    }

    logout() {
    }

}
