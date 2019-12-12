import {Component, OnInit} from '@angular/core';
import {Admin} from '../../models';
import {StoreService} from "../../services/store.service";
import {AdminService} from "../../services/admin.service";

@Component({
    templateUrl: './admin.info.component.html'
})
export class AdminInfoComponent implements OnInit {
    private adminId: number;
    private admin: Admin;

    constructor(private storeService: StoreService,
                private adminService: AdminService) {
        this.adminId = this.storeService.getId();
        this.adminService.getById(this.adminId).subscribe(data => this.admin = data);
    }

    ngOnInit() {
    }
}
