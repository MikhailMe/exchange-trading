import {Client} from "./client.model";
import {BrokerRequest} from "../requests/broker-request.model";
import {Person} from "./person.model";

export interface Broker extends Person{
    clients: Client[];
    requests: BrokerRequest[];
}
