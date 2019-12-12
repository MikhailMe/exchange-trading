import {Person} from "./person.model";
import {Transaction} from "../treasury/transaction.model";

export interface Admin extends Person {
    brokers: number[];
    transactions: Transaction[];
}
