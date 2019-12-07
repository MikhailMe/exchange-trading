import {Person} from "./person.model";
import {Agreement, BrokerageAccount, ClientRequest, Passport, Transaction} from "..";

export interface Client extends Person {
    passport: Passport;
    agreement: Agreement;
    requests: ClientRequest[];
    transactions: Transaction[];
    brokerageAccount: BrokerageAccount;
}
