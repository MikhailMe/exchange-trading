import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    templateUrl: './client.passport.component.html',
    styleUrls: ['./client.passport.component.css']
})
export class ClientPassportComponent implements OnInit {
    protected series: number;
    protected number: number;

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    updatePassport() {
        return this.router.navigateByUrl('/client/info')
    }
}
