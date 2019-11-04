import {Person} from "./person.model";
import {Broker} from "./broker.model";
import {Agreement, BrokerageAccount, Passport} from "..";
import {ClientRequest} from "../requests/client-request.model";

export interface Client extends Person {
    broker: Broker;
    passport: Passport;
    agreement: Agreement;
    requests: ClientRequest[];
    brokerageAccount: BrokerageAccount;
}
