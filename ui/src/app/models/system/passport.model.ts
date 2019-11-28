import {Entity} from "../entity.model";

export interface Passport extends Entity {
    series: number;
    number: number;
}
