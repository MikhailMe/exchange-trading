import {Component, OnInit} from '@angular/core';
import {BankRecord} from "../../models";
import {DataService} from "../../services/data.service";

@Component({
    templateUrl: './admin.bank.component.html'
})
export class AdminBankComponent implements OnInit {
    protected bank: BankRecord[];

    constructor(dataService: DataService) {
        this.bank = dataService.getBankRecords();
    }

    ngOnInit() {
    }

}
