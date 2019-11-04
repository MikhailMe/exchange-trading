import {Admin, Client} from "..";

export class Transfer {
    client: Client;
    money: number;
    date: Date;
    approver: Admin;
}
