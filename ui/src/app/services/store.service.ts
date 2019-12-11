import {Injectable} from '@angular/core';
import {Asset, Person, Stock, Transaction} from '../models';

@Injectable()
export class StoreService {

    private id: number;
    private propertyId: number;

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

}
