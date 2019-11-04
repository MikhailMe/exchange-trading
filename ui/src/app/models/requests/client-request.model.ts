import {BrokerageAccount, Passport} from "..";

export class ClientRequest extends Request {
    passport: Passport;
    brokerageAccount: BrokerageAccount;
}
