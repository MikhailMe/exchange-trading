import {Person} from "./person.model";
import {Agreement} from "..";

export interface Broker extends Person {
    adminId: number;
    agreements: Agreement[];
}
