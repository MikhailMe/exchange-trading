import {Component, OnInit} from '@angular/core';
import {BankRecord} from "../../models";
import {AdminService} from "../../services/admin.service";

@Component({
    templateUrl: './admin.bank.component.html'
})
export class AdminBankComponent implements OnInit {
    protected bank: BankRecord[];

    constructor(private adminService: AdminService) {
        this.adminService.getBankMoney().subscribe(data => this.bank = data);
    }

    ngOnInit() {
    }

}
