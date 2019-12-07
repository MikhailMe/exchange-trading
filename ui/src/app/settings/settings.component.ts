import {Component, OnInit} from '@angular/core';
import {DataService} from '../services/data.service';

@Component({
    selector: 'app-settings-page',
    templateUrl: './settings.component.html',
    styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

    public requests: any[];

    constructor(private readonly dataService: DataService) {
        this.requests = dataService.getData();
    }

    ngOnInit() {

    }

}
