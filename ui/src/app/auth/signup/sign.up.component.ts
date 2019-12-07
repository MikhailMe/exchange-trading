import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Person} from "../../models";
import {Router} from "@angular/router";
import {Client} from "../../models/actors/client.model";

@Component({
    templateUrl: './sign.up.component.html',
    styleUrls: ['./sign.up.component.css']
})
export class SignUpComponent implements OnInit {
    protected login: string;
    protected password: string;
    protected password_repeat: string;
    protected personType: string;
    protected name: string;
    protected surname: string;
    protected person: Person;
    protected readonly authService: AuthService;

    constructor(private router: Router, authService: AuthService) {
        this.authService = authService;
    }

    ngOnInit() {
    }

    signUp() {
        if (this.password != this.password_repeat) {
            alert("passwords don't match");
        } else {
            this.router.navigateByUrl('/signin');
            /*this.authService.signUp(this.login, this.password, this.personType, this.name, this.surname).subscribe(
                data => {
                    if (this.personType == 'client') {
                        this.person = <Client> data;
                    }
                }, error => console.error(error)
            );
            if (this.person) {
                console.log(this.person.name);
                this.router.navigateByUrl('/signin');
            }*/
        }
    }

}
