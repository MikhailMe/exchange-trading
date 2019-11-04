import {Client} from "..";
import {RequestType} from "../../enums/request-type";

export class BrokerRequest extends Request {
    client: Client;
    money: number;
    requestType: RequestType;
}
