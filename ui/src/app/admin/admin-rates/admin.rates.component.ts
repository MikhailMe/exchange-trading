import {Component, OnInit} from '@angular/core';
import {Rate} from "../../models";
import {AdminService} from "../../services/admin.service";

@Component({
    templateUrl: './admin.rates.component.html'
})
export class AdminRatesComponent implements OnInit {
    protected rates: Rate[];

    constructor(private adminService: AdminService) {
        this.adminService.getRates().subscribe(data => this.rates = data);
    }

    ngOnInit() {
    }

}
