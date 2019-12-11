import {Entity} from '../entity.model';
import {Asset} from '..';
import {Stock} from '..';

export class BrokerageAccount extends Entity {
    clientPassportId: number;
    creationDate: Date;
    assets: Asset[];
    stocks: Stock[];
}
