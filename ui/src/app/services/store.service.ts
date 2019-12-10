import {Injectable} from '@angular/core';
import {Asset, Person, Stock, Transaction} from '../models';

@Injectable()
export class StoreService {

    private id: number;
    private propertyId: number;


    private asset: Asset;
    private stock: Stock;
    private person: Person;
    private transaction: Transaction;

    constructor() {
    }

    getId() {
        return this.id;
    }

    setId(id: number) {
        this.id = id;
    }

    getPropertyId() {
        return this.propertyId;
    }

    setPropertyId(propertyId: number) {
        this.propertyId = propertyId;
    }


    getAsset() {
        return this.asset;
    }

    setAsset(asset: Asset) {
        this.asset = asset;
    }

    getStock() {
        return this.stock;
    }

    setStock(stock: Stock) {
        this.stock = stock;
    }

    getPerson(): Person {
        return this.person;
    }

    setPerson(user: Person) {
        this.person = user;
    }

    getTransaction() {
        return this.transaction;
    }

    setTransaction(transaction: Transaction) {
        this.transaction = transaction;
    }
}
