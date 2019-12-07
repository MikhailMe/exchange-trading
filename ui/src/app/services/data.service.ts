import { Injectable } from '@angular/core';
import {Agreement, ClientRequest, Transaction} from "../models";

@Injectable()
export class DataService {

    constructor() { }

    getData() {
        return [
            {id: 1, requestType: 'type1'},
            {id: 2, requestType: 'type1'},
            {id: 3, requestType: 'type1'},
            {id: 4, requestType: 'type1'},
            {id: 5, requestType: 'type1'},
            {id: 6, requestType: 'type1'},
            {id: 7, requestType: 'type1'},
            {id: 8, requestType: 'type1'},
            {id: 9, requestType: 'type1'},
        ];
    }

    getRequests(): ClientRequest[] {
        return [
            {date: null, id: 1, adminId: 1, brokerId: 1, clientId: 1, fromType: 'lol', quantity: 11, requestType: 'm2s', status: 'a', toType: 'lal'},
            {date: null, id: 2, adminId: 2, brokerId: 2, clientId: 2, fromType: 'lol', quantity: 22, requestType: 'm2s', status: 'a', toType: 'lal'},
            {date: null, id: 3, adminId: 3, brokerId: 3, clientId: 3, fromType: 'lol', quantity: 33, requestType: 'm2s', status: 'a', toType: 'lal'},
            {date: null, id: 4, adminId: 4, brokerId: 4, clientId: 4, fromType: 'lol', quantity: 44, requestType: 'm2s', status: 'a', toType: 'lal'},
            {date: null, id: 5, adminId: 5, brokerId: 5, clientId: 5, fromType: 'lol', quantity: 55, requestType: 'm2s', status: 'a', toType: 'lal'},
            {date: null, id: 6, adminId: 6, brokerId: 6, clientId: 6, fromType: 'lol', quantity: 66, requestType: 'm2s', status: 'a', toType: 'lal'},
            {date: null, id: 7, adminId: 7, brokerId: 7, clientId: 7, fromType: 'lol', quantity: 77, requestType: 'm2s', status: 'a', toType: 'lal'},
            {date: null, id: 8, adminId: 8, brokerId: 8, clientId: 8, fromType: 'lol', quantity: 88, requestType: 'm2s', status: 'a', toType: 'lal'},
            {date: null, id: 9, adminId: 9, brokerId: 9, clientId: 9, fromType: 'lol', quantity: 99, requestType: 'm2s', status: 'a', toType: 'lal'},
        ]
    }

    getTransactions(): Transaction[] {
        return [
            {clientId: 1, adminId: 1, asset: null, stock: null, date: null, id: 1, type: 'type1'},
            {clientId: 2, adminId: 1, asset: null, stock: null, date: null, id: 1, type: 'type2'},
            {clientId: 3, adminId: 1, asset: null, stock: null, date: null, id: 1, type: 'type3'},
            {clientId: 4, adminId: 1, asset: null, stock: null, date: null, id: 1, type: 'type4'},
            {clientId: 5, adminId: 1, asset: null, stock: null, date: null, id: 1, type: 'type5'},
            {clientId: 6, adminId: 1, asset: null, stock: null, date: null, id: 1, type: 'type6'},
            {clientId: 7, adminId: 1, asset: null, stock: null, date: null, id: 1, type: 'type7'},
            {clientId: 8, adminId: 1, asset: null, stock: null, date: null, id: 1, type: 'type8'},
            {clientId: 9, adminId: 1, asset: null, stock: null, date: null, id: 1, type: 'type9'},
        ];
    }

    getAgreements(): Agreement[] {
        return [
            {id: 1, brokerId: 1, clientId: 1, startDate: null, validity: 'year'},
            {id: 2, brokerId: 2, clientId: 2, startDate: null, validity: 'year'},
            {id: 3, brokerId: 3, clientId: 3, startDate: null, validity: 'year'},
            {id: 4, brokerId: 4, clientId: 4, startDate: null, validity: 'year'},
            {id: 5, brokerId: 5, clientId: 5, startDate: null, validity: 'year'},
            {id: 6, brokerId: 6, clientId: 6, startDate: null, validity: 'year'},
            {id: 7, brokerId: 7, clientId: 7, startDate: null, validity: 'year'},
            {id: 8, brokerId: 8, clientId: 8, startDate: null, validity: 'year'},
            {id: 9, brokerId: 9, clientId: 9, startDate: null, validity: 'year'}
        ];
    }
}
