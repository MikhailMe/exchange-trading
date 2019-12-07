import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Client, Person} from "../../models";
import {Router} from "@angular/router";

@Component({
    templateUrl: './sign.in.component.html',
    styleUrls: ['./sign.in.component.css']
})
export class SignInComponent implements OnInit {
    public login: string;
    public password: string;
    public personType: string;
    public person: Person;
    protected readonly authService: AuthService;

    constructor(private router: Router, authService: AuthService) {
        this.authService = authService;
    }

    ngOnInit() {
    }

    signIn() {
        if (this.login.length == 0 || this.password.length == 0 || this.personType.length == 0) {
            alert("You must fill all fields")
        }
        this.router.navigateByUrl('/client/base');
        /*this.authService.signIn(this.login, this.password, this.personType).subscribe(
            data => {
                this.person = <Client> data
            }, error => console.error(error));

        if (this.person) {
            console.log(this.person.name);
            this.router.navigateByUrl('/client/base');
        }*/
    }
}
