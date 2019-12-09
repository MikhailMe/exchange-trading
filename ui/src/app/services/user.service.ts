import {Injectable} from '@angular/core';
import {Person} from '../models';

@Injectable()
export class UserService {

    private person: Person;

    constructor() {
    }

    setUser(user: Person) {
        this.person = user;
    }

    getUser(): Person {
        return this.person;
    }

}
