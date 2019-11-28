import {Entity} from "../entity.model";
import {Asset} from "../treasury/asset.model";
import {Stock} from "../treasury/stock.model";

export class BrokerageAccount extends Entity {
    clientPassportId: number;
    creationDate: Date;
    assets: Asset[];
    stocks: Stock[];
}
