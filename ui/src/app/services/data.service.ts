import { Injectable } from '@angular/core';

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
}
