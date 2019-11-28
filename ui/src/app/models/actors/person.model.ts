import {Entity} from "../entity.model";
import {Credentials} from "..";

export interface Person extends Entity {
    name: string;
    surname: string;
    personType: string;
    isAuthenticated: boolean;
    credentials: Credentials;
}
