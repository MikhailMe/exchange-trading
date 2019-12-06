import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CommonModule} from '@angular/common';
import {SettingsComponent} from './settings/settings.component';
import {OneMoreComponent} from './onemore/one-more.component';
import {DataService} from './services/data.service';
import {SignInComponent} from './auth/signin/sign.in.component';
import {SignUpComponent} from './auth/signup/sign.up.component';
import {AuthService} from './services/auth.service';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

@NgModule({
    declarations: [
        AppComponent,
        SettingsComponent,
        OneMoreComponent,
        SignInComponent,
        SignUpComponent
    ],
    imports: [
        CommonModule,
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule
    ],
    providers: [
        DataService,
        AuthService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
