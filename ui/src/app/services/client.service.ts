import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {Agreement, Asset, BrokerageAccount, Client, ClientRequest, Stock, Transaction} from '../models';

@Injectable()
export class ClientService {
    protected readonly http: HttpClient;

    constructor(http: HttpClient) {
        this.http = http;
    }

    public getById(clientId: number) {
        const url = this.urlWithClientId(environment.getClientById, clientId);
        return this.http.get<Client>(url);
    }

    public setClientPassport(clientId: number, series: number, number: number) {
        const url = `${environment.getClientInfo}${clientId}/${environment.setClientPassport}`;
        return this.http.post<Client>(url, {series, number});
    }

    openBrokerageAccount(clientId: number) {
        const url = this.urlWithClientId(environment.openBrokerageAccount, clientId);
        return this.http.post<BrokerageAccount>(url, {});
    }

    closeBrokerageAccount(clientId: number) {
        const url = this.urlWithClientId(environment.closeBrokerageAccount, clientId);
        return this.http.post<boolean>(url, {});
    }

    putMoneyToAccount(clientId: number, money: number, currency: string) {
        const url = this.urlWithClientId(environment.putMoneyToAccount, clientId);
        return this.http.post<boolean>(url, {money, currency});
    }

    makeBrokerAgreement(clientId: number, validity: string) {
        const url = this.urlWithClientId(environment.makeBrokerAgreement, clientId);
        return this.http.post<Agreement>(url, {validity});
    }

    extendBrokerAgreement(clientId: number, validity: string) {
        const url = this.urlWithClientId(environment.extendBrokerAgreement, clientId);
        return this.http.post<Agreement>(url, {validity});
    }

    breakBrokerAgreement(clientId: number) {
        const url = this.urlWithClientId(environment.breakBrokerAgreement, clientId);
        return this.http.post<boolean>(url, {});
    }

    exchangeMoneyForStocks(clientId: number, quantity: number, fromType: string, toType: string) {
        const url = this.urlWithClientId(environment.exchangeMoneyToStocks, clientId);
        return this.http.post<ClientRequest>(url, {quantity, fromType, toType});
    }

    exchangeStocksToMoney(clientId: number, quantity: number, fromType: string, toType: string) {
        const url = this.urlWithClientId(environment.exchangeStocksToMoney, clientId);
        return this.http.post<ClientRequest>(url, {quantity, fromType, toType});
    }

    getTransactions(clientId: number) {
        const url = this.urlWithClientId(environment.getClientTransactions, clientId);
        return this.http.get<Transaction[]>(url);
    }

    getTransactionById(clientId: number, transactionId: number) {
        const url = this.urlWithClientIdAndPropertyId(environment.getClientTransactionById, clientId, transactionId);
        return this.http.get<Transaction>(url);
    }

    getRequests(clientId: number) {
        const url = this.urlWithClientId(environment.getClientRequests, clientId);
        return this.http.get<ClientRequest[]>(url);
    }

    getRequestById(clientId: number, requestId: number) {
        const url = this.urlWithClientIdAndPropertyId(environment.getClientRequestById, clientId, requestId);
        return this.http.get<ClientRequest>(url);
    }

    getAssets(clientId: number) {
        const url = this.urlWithClientId(environment.getClientAssets, clientId);
        return this.http.get<Asset[]>(url);
    }

    getAssetById(clientId: number, assetId: number) {
        const url = this.urlWithClientIdAndPropertyId(environment.getClientAssetById, clientId, assetId);
        return this.http.get<Asset>(url);
    }

    getStocks(clientId: number) {
        const url = this.urlWithClientId(environment.getClientStocks, clientId);
        return this.http.get<Stock[]>(url);
    }

    getStockById(clientId: number, stockId: number) {
        const url = this.urlWithClientIdAndPropertyId(environment.getClientStockById, clientId, stockId);
        return this.http.get<Stock>(url);
    }

    private urlWithClientId(urlWithoutId: string, clientId: number): string {
        return urlWithoutId.replace(':clientId', `${clientId}`);
    }

    private urlWithClientIdAndPropertyId(urlWithoutId: string, clientId: number, id: number): string {
        return urlWithoutId
            .replace(':clientId', `${clientId}`)
            .replace(':id', `${id}`);
    }
}
