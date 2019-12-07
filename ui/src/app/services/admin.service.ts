import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {BankRecord, ClientRequest, Rate, Transaction} from "../models";

@Injectable()
export class AdminService {
    protected readonly http: HttpClient;

    constructor(http: HttpClient) {
        this.http = http;
    }

    checkRequests(adminId: number) {
        const url = `${adminId}/` + environment.checkAdminRequests;
        return this.http.get<ClientRequest[]>(url);
    }

    approveRequest(adminId: number, clientRequestId: number) {
        const url = `${adminId}/` + environment.approveRequest;
        return this.http.post<Transaction>(url, {clientRequestId});
    }

    declineRequest(clientRequestId: number) {
        const url = environment.declineRequest + `${clientRequestId}`;
        return this.http.post<string>(url, {});
    }

    getRates() {
        const url = environment.getRates;
        return this.http.get<Rate[]>(url);
    }

    getBankMoney() {
        const url = environment.getBankAssets;
        return this.http.get<BankRecord[]>(url);
    }
}
