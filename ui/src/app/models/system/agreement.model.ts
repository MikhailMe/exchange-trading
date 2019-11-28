import {Entity} from "../entity.model";

export interface Agreement extends Entity {
    clientId: number;
    brokerId: number;
    validity: string;
    startDate: Date;
}
