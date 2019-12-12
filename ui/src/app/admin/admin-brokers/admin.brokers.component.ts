import {Component, OnInit} from '@angular/core';
import {Broker} from "../../models";
import {AdminService} from "../../services/admin.service";
import {StoreService} from "../../services/store.service";

@Component({
    templateUrl: './admin.brokers.component.html'
})
export class AdminBrokersComponent implements OnInit {
    private adminId: number;
    private adminBrokers: Broker[];

    constructor(private storeService: StoreService,
                private adminService: AdminService) {
        this.adminId = this.storeService.getId();
        this.adminService.getBrokers(this.adminId).subscribe(data => this.adminBrokers = data);
    }

    ngOnInit() {
    }
}
