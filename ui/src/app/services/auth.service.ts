import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../core/enviroment';
import {Person} from '../models';

@Injectable()
export class AuthService {
    protected readonly http: HttpClient;

    constructor(http: HttpClient) {
        this.http = http;
    }

    signIn(login: string, password: string, personType: string) {
        const url = environment.signIn;
        return this.http.post<any>(url, {login, password, personType});
    }

    signUp(login: string, password: string, personType: string, name: string, surname: string) {
        const url = environment.signUp;
        return this.http.post<Person>(url, {login, password, personType, name, surname});
    }

    signOut(id: number, personType: string) {
        const url = environment.signOut;
        return this.http.post(url, {id, personType});
    }
}
