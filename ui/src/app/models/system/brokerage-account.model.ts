import {Stock} from "./stock.model";

export class BrokerageAccount {
    number: number;
    money: number;
    currency: string;
    creationDate: Date;
    stock: Stock;
}
