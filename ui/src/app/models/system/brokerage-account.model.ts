import {Entity} from "../entity.model";
import {Asset} from "../treasury/asset.model";
import {Stock} from "../treasury/stock.model";
import {ClientRequest} from "../client-request.model";
import {Transaction} from "..";

export class BrokerageAccount extends Entity {
    clientPassportId: number;
    creationDate: Date;
    assets: Asset[];
    stocks: Stock[];
    requests: ClientRequest[];
    transactions: Transaction[];
}
