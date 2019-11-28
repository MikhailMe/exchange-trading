import {Entity} from "../entity.model";

export interface Asset extends Entity {
    clientId: number;
    type: string;
    quantity: number;
}
