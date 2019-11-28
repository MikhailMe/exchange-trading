import {Entity} from "./entity.model";

export class ClientRequest extends Entity {
    date: Date;
    adminId: number;
    brokerId: number;
    clientId: number;
    quantity: number;
    fromType: string;
    toType: string;
    status: string;
    requestType: string;
}
