import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {StoreService} from "../../services/store.service";
import {Router} from "@angular/router";

@Component({
    templateUrl: './admin.base.component.html'
})
export class AdminBaseComponent implements OnInit {
    private adminId: number;

    constructor(private router: Router,
                private authService: AuthService,
                private storeService: StoreService,) {
        this.adminId = this.storeService.getId();
    }

    ngOnInit() {
    }

    signOut() {
        this.authService.signOut(this.adminId, 'admin')
            .subscribe(() =>  this.router.navigateByUrl('/'));
    }
}
