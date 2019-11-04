import {Broker} from "./broker.model";
import {AdminRequest} from "../requests/admin-request.model";
import {Person} from "./person.model";

export interface Admin extends Person {
    brokers: Broker[];
    requests: AdminRequest[];
}
