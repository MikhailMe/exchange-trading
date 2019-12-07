import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SettingsComponent} from './settings/settings.component';
import {OneMoreComponent} from './onemore/one-more.component';
import {SignInComponent} from './auth/signin/sign.in.component';
import {SignUpComponent} from './auth/signup/sign.up.component';

const routes: Routes = [
    {path: 'setting', component: SettingsComponent},
    {path: 'onemore', component: OneMoreComponent},
    {path: 'signin', component: SignInComponent},
    {path: 'signup', component: SignUpComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
