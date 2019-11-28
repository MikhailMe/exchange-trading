import {Entity} from "../entity.model";

export interface Stock extends Entity {
    clientId: number;
    stockType: string;
    quantity: number;
}
