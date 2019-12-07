import {Asset} from "./asset.model";
import {Stock} from "./stock.model";
import {Entity} from "../entity.model";

export interface Transaction extends Entity {
    adminId: number;
    clientId: number;
    type: string;
    date: Date;
    asset: Asset;
    stock: Stock;
}
