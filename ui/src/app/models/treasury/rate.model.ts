import {Entity} from "../entity.model";

export interface Rate extends Entity {
    toType: string;
    fromType: string;
    rate: number;
    date: Date;
}
