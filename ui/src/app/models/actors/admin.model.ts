import {Broker} from "./broker.model";
import {Person} from "./person.model";
import {Transaction} from "../treasury/transaction.model";

export interface Admin extends Person {
    brokers: Broker[];
    transactions: Transaction[];
}
