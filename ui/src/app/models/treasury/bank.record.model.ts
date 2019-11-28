import {Entity} from "../entity.model";

export interface BankRecord extends Entity {
    quantity: number;
    type: string;
}
