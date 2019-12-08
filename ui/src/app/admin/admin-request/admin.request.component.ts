import {Component, OnInit} from '@angular/core';
import {ClientRequest} from "../../models";
import {Router} from "@angular/router";

@Component({
    templateUrl: './admin.request.component.html'
})
export class AdminRequestComponent implements OnInit {
    protected request: ClientRequest;

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    approve() {
        this.router.navigateByUrl('/admin/requests');
    }

    decline() {
        this.router.navigateByUrl('/admin/requests');
    }
}
