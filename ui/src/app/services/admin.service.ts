import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {Admin, BankRecord, ClientRequest, Rate, Transaction} from '../models';
import {Broker} from '../models';

@Injectable()
export class AdminService {
    protected readonly http: HttpClient;

    constructor(http: HttpClient) {
        this.http = http;
    }

    checkRequests(adminId: number) {
        const url = this.urlWithAdminId(environment.checkAdminRequests, adminId);
        return this.http.get<ClientRequest[]>(url);
    }

    approveRequest(adminId: number, clientRequestId: number) {
        let url = this.urlWithAdminId(environment.approveRequest, adminId);
        return this.http.post<Transaction>(url, {clientRequestId});
    }

    declineRequest(clientRequestId: number) {
        const url = this.urlWithRequestId(environment.declineRequest, clientRequestId);
        return this.http.post(url, {});
    }

    getRates() {
        const url = environment.getRates;
        return this.http.get<Rate[]>(url);
    }

    getBankMoney() {
        const url = environment.getBankAssets;
        return this.http.get<BankRecord[]>(url);
    }

    getById(adminId: number) {
        const url = this.urlWithAdminId(environment.getAdminById, adminId);
        return this.http.get<Admin>(url);
    }

    getBrokers(adminId: number) {
        const url = this.urlWithAdminId(environment.getAdminBrokers, adminId);
        return this.http.get<Broker[]>(url);
    }

    private urlWithAdminId(urlWithoutId: string, adminId: number): string {
        return urlWithoutId.replace(':adminId', `${adminId}`);
    }

    private urlWithRequestId(urlWithoutId: string, clientRequestId: number): string {
        return urlWithoutId.replace(':clientRequestId', `${clientRequestId}`);
    }
}
