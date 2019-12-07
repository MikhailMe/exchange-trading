import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {Agreement, BrokerageAccount, Client, ClientRequest, Person, Transaction} from "../models";

@Injectable()
export class ClientService {
    protected readonly http: HttpClient;

    constructor(http: HttpClient) {
        this.http = http;
    }

    setClientPassport(clientId: number, series: number, number: number) {
        const url = environment.setClientPassport + `/${clientId}`;
        return this.http.post<Client>(url, {series, number});
    }

    openBrokerageAccount(clientId: number) {
        const url = environment.openBrokerageAccount + `/${clientId}`;
        return this.http.post<BrokerageAccount>(url, {});
    }

    closeBrokerageAccount(clientId: number) {
        const url = environment.closeBrokerageAccount + `/${clientId}`;
        return this.http.post<boolean>(url, {});
    }

    putMoneyToAccount(clientId: number, money: number, currency: string) {
        const url = `${clientId}/` + environment.putMoneyToAccount;
        return this.http.post<boolean>(url, {money, currency});
    }

    makeBrokerAgreement(clientId: number, validity: string) {
        const url = `${clientId}/` + environment.makeBrokerAgreement;
        return this.http.post<Agreement>(url, {validity});
    }

    extendBrokerAgreement(clientId: number, validity: string) {
        const url = `${clientId}/` + environment.extendBrokerAgreement;
        return this.http.post<Agreement>(url, {validity});
    }

    breakBrokerAgreement(clientId: number) {
        const url = `${clientId}/` + environment.breakBrokerAgreement;
        return this.http.post<boolean>(url, {});
    }

    exchangeMoneyForStocks(clientId: number, quantity: number, fromType: string, toType: string) {
        const url = `${clientId}/` + environment.exchangeMoneyToStocks;
        return this.http.post<ClientRequest>(url, {quantity, fromType, toType});
    }

    exchangeStocksToMoney(clientId: number, quantity: number, fromType: string, toType: string) {
        const url = `${clientId}/` + environment.exchangeStocksToMoney;
        return this.http.post<ClientRequest>(url, {quantity, fromType, toType});
    }

    getTransactions(clientId: number) {
        const url = `${clientId}` + environment.getClientTransactions;
        return this.http.get<Transaction[]>(url);
    }
}
