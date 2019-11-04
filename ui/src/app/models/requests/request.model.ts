import {Person} from "..";

export class Request {
    to: Person;
    from: Person;
    date: Date;

    public Request(to: Person, from: Person, date: Date) {
        this.to = to;
        this.from = from;
        this.date = date;
    }
}
