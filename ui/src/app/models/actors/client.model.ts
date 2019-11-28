import {Person} from "./person.model";
import {Agreement, BrokerageAccount, Passport} from "..";

export interface Client extends Person {
    passport: Passport;
    agreement: Agreement;
    requests: number[];
    transactions: number[];
    brokerageAccount: BrokerageAccount;
}
