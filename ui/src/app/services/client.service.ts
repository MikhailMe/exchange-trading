import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {Agreement, BrokerageAccount, Client, ClientRequest, Transaction} from "../models";

@Injectable()
export class ClientService {
    protected readonly http: HttpClient;

    constructor(http: HttpClient) {
        this.http = http;
    }

    public getById(clientId: number) {
        const url = this.urlWithId(environment.getClientById, clientId);
        return this.http.get<Client>(url);
    }

    public setClientPassport(clientId: number, series: number, number: number) {
        const url = this.urlWithId(environment.setClientPassport, clientId);
        return this.http.post<Client>(url, {series, number});
    }

    openBrokerageAccount(clientId: number) {
        const url = this.urlWithId(environment.openBrokerageAccount, clientId);
        return this.http.post<BrokerageAccount>(url, {});
    }

    closeBrokerageAccount(clientId: number) {
        const url = this.urlWithId(environment.closeBrokerageAccount, clientId);
        return this.http.post<boolean>(url, {});
    }

    putMoneyToAccount(clientId: number, money: number, currency: string) {
        const url = this.urlWithId(environment.putMoneyToAccount, clientId);
        return this.http.post<boolean>(url, {money, currency});
    }

    makeBrokerAgreement(clientId: number, validity: string) {
        const url = this.urlWithId(environment.makeBrokerAgreement, clientId);
        return this.http.post<Agreement>(url, {validity});
    }

    extendBrokerAgreement(clientId: number, validity: string) {
        const url = this.urlWithId(environment.extendBrokerAgreement, clientId);
        return this.http.post<Agreement>(url, {validity});
    }

    breakBrokerAgreement(clientId: number) {
        const url = this.urlWithId(environment.breakBrokerAgreement, clientId);
        return this.http.post<boolean>(url, {});
    }

    exchangeMoneyForStocks(clientId: number, quantity: number, fromType: string, toType: string) {
        const url = this.urlWithId(environment.exchangeMoneyToStocks, clientId);
        return this.http.post<ClientRequest>(url, {quantity, fromType, toType});
    }

    exchangeStocksToMoney(clientId: number, quantity: number, fromType: string, toType: string) {
        const url = this.urlWithId(environment.exchangeStocksToMoney, clientId);
        return this.http.post<ClientRequest>(url, {quantity, fromType, toType});
    }

    getTransactions(clientId: number) {
        const url = this.urlWithId(environment.getClientTransactions, clientId);
        return this.http.get<Transaction[]>(url);
    }

    private urlWithId(urlWithoutId: string, clientId: number): string {
        return urlWithoutId.replace(':clientId', `${clientId}`)
    }
}
