import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../core/enviroment';

@Injectable()
export class AuthService {
    protected readonly http: HttpClient;

    constructor(http: HttpClient) {
        this.http = http;
    }

    signIn(login: string, password: string, personType: string) {
        const url = environment.signIn;
        const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
        headers.set('Access-Control-Allow-Origin', 'localhost:8080');
        return this.http.post<any>(url, {login, password, personType}, {headers});
    }

    signUp(login: string, password: string, personType: string, name: string, surname: string) {
        const url = environment.signUp;
        return this.http.post(url, {login, password, personType, name, surname});
    }

    signOut(id: number, personType: string) {
        const url = environment.signOut;
        return this.http.post(url, {id, personType}).pipe();
    }
}
