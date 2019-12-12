import {Component, OnInit} from '@angular/core';
import {ClientRequest} from "../../models";
import {Router} from "@angular/router";
import {AdminService} from "../../services/admin.service";
import {StoreService} from "../../services/store.service";

@Component({
    templateUrl: './admin.requests.component.html'
})
export class AdminRequestsComponent implements OnInit {
    protected adminId: number;
    protected adminRequests: ClientRequest[];

    constructor(private storeService: StoreService,
                private adminService: AdminService,
                private router: Router) {
        this.adminId = this.storeService.getId();
        this.adminService.checkRequests(this.adminId).subscribe(data => this.adminRequests = data);
    }

    ngOnInit() {
    }

    onRequestClick(request: ClientRequest) {
        this.storeService.setPropertyId(request.id);
        return this.router.navigateByUrl(`/admin/request/${request.id}`);
    }
}
