import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SettingsComponent} from './settings/settings.component';
import {SignInComponent} from './auth/signin/sign.in.component';
import {SignUpComponent} from './auth/signup/sign.up.component';
import {ClientBaseComponent} from "./client/client-base/client.base.component";
import {ClientInfoComponent} from "./client/client-info/client.info.component";
import {HomeComponent} from "./home/home.component";
import {ClientPassportComponent} from "./client/client-passport/client.passport.component";

const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'setting', component: SettingsComponent},
    {path: 'signin', component: SignInComponent},
    {path: 'signup', component: SignUpComponent},
    {path: 'client/base', component: ClientBaseComponent},
    {path: 'client/info', component: ClientInfoComponent},
    {path: 'client/passport', component: ClientPassportComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
