import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {BankRecord, ClientRequest, Rate, Transaction} from "../models";

@Injectable()
export class BrokerService {
    protected readonly http: HttpClient;

    constructor(http: HttpClient) {
        this.http = http;
    }

    checkRequests(brokerId: number) {
        const url = `${brokerId}/` + environment.checkBrokerRequests;
        return this.http.get<ClientRequest[]>(url);
    }

    validateClientRequest(brokerId: number, clientRequestId: number) {
        const url = `${brokerId}/` + environment.validateClientRequest;
        return this.http.post<ClientRequest[]>(url, {clientRequestId});
    }

    approveClientRequest(brokerId: number, clientRequestId: number) {
        const url = `${brokerId}/` + environment.approveClientRequest;
        return this.http.post<ClientRequest[]>(url, {clientRequestId});
    }

    declineClientRequest(brokerId: number, clientRequestId: number) {
        const url = `${brokerId}/` + environment.declineClientRequest;
        return this.http.post<ClientRequest[]>(url, {clientRequestId});
    }

}
