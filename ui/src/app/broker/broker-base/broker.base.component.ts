import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
import {StoreService} from '../../services/store.service';

@Component({
    templateUrl: './broker.base.component.html'
})
export class BrokerBaseComponent implements OnInit {
    private brokerId: number;

    constructor(private router: Router,
                private authService: AuthService,
                private storeService: StoreService) {
        this.brokerId = this.storeService.getId();
    }

    ngOnInit() {
    }

    signOut() {
        this.authService.signOut(this.brokerId, 'broker')
            .subscribe(() => this.router.navigateByUrl('/'));
    }
}
