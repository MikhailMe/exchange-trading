import {Component, OnInit} from '@angular/core';
import {Rate} from "../../models";
import {DataService} from "../../services/data.service";

@Component({
    templateUrl: './admin.rates.component.html'
})
export class AdminRatesComponent implements OnInit {
    protected rates: Rate[];

    constructor(dataService: DataService) {
        this.rates = dataService.getRates();
    }

    ngOnInit() {
    }

}
