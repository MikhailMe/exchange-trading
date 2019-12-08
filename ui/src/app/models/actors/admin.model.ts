import {Person} from "./person.model";
import {Transaction} from "../treasury/transaction.model";
import {Broker} from "./broker.model";

export interface Admin extends Person {
    brokers: Broker[];
    transactions: Transaction[];
}
