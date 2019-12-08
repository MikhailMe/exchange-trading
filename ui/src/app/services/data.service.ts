import { Injectable } from '@angular/core';
import {Agreement, BankRecord, Broker, ClientRequest, Rate, Transaction} from "../models";

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

    getBrokers(): Broker[] {
        return [
            {id: 1, credentials: null, name: 'lol', surname: 'lolov', adminId: 1, agreements: null, personType: 'broker', isAuthenticated: true},
            {id: 2, credentials: null, name: 'lal', surname: 'lalov', adminId: 2, agreements: null, personType: 'broker', isAuthenticated: true},
            {id: 3, credentials: null, name: 'lil', surname: 'lilov', adminId: 3, agreements: null, personType: 'broker', isAuthenticated: true},
            {id: 4, credentials: null, name: 'lul', surname: 'lulov', adminId: 4, agreements: null, personType: 'broker', isAuthenticated: true},
            {id: 5, credentials: null, name: 'lyl', surname: 'lylov', adminId: 5, agreements: null, personType: 'broker', isAuthenticated: true},
            {id: 6, credentials: null, name: 'lel', surname: 'lelov', adminId: 6, agreements: null, personType: 'broker', isAuthenticated: true},
        ];
    }

    getRates(): Rate[] {
        return [
            {id: 1, date: null, fromType: 'a', toType: 'b', rate: 101},
            {id: 2, date: null, fromType: 'b', toType: 'c', rate: 102},
            {id: 3, date: null, fromType: 'c', toType: 'd', rate: 103},
            {id: 4, date: null, fromType: 'd', toType: 'e', rate: 104},
            {id: 5, date: null, fromType: 'e', toType: 'f', rate: 105},
            {id: 6, date: null, fromType: 'f', toType: 'g', rate: 106},
            {id: 7, date: null, fromType: 'g', toType: 'h', rate: 107},
            {id: 8, date: null, fromType: 'h', toType: 'i', rate: 108},
            {id: 9, date: null, fromType: 'i', toType: 'j', rate: 109},
        ];
    }

    getBankRecords(): BankRecord[] {
        return [
            {id: 1, type: 'a', quantity: 101},
            {id: 2, type: 'b', quantity: 202},
            {id: 3, type: 'c', quantity: 303},
            {id: 4, type: 'd', quantity: 404},
            {id: 5, type: 'e', quantity: 505},
            {id: 6, type: 'f', quantity: 606},
            {id: 7, type: 'g', quantity: 707},
            {id: 8, type: 'h', quantity: 808},
            {id: 9, type: 'i', quantity: 909},
        ];
    }
}
