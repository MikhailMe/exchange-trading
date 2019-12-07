import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {CommonModule} from '@angular/common';
import {SettingsComponent} from './settings/settings.component';
import {DataService} from './services/data.service';
import {HomeComponent} from "./home/home.component";
import {SignInComponent} from './auth/signin/sign.in.component';
import {SignUpComponent} from './auth/signup/sign.up.component';
import {AuthService} from './services/auth.service';
import {HttpClientModule} from '@angular/common/http';
import {ClientBaseComponent} from "./client/client-base/client.base.component";
import {ClientInfoComponent} from "./client/client-info/client.info.component";
import {ClientPassportComponent} from "./client/client-passport/client.passport.component";
import {ClientBrokerageAccountComponent} from "./client/client-brokerage-account/client.brokerage.account.component";
import {ClientBrokerageAccountInfoComponent} from "./client/client-brokerage-account-info/client.brokerage.account.info.component";
import {ClientPutMoneyComponent} from "./client/client-put-money/client.put.money.component";
import {ClientExchangeComponent} from "./client/client-exchange/client.exchange.component";
import {ClientAgreementComponent} from "./client/client-argreement/client.agreement.component";
import {ClientAgreementExtendComponent} from "./client/client-agreement-extend/client.agreement.extend.component";
import {ClientAgreementInfoComponent} from "./client/client-agreement-info/client.agreement.info.component";
import {ClientExchangeMoneyToStocksComponent} from "./client/client-exchange-money-to-stocks/client.exchange.money.to.stocks.component";
import {ClientExchangeStocksToMoneyComponent} from "./client/client-exchange-stocks-to-money/client.exchange.stocks.to.money.component";
import {ClientRequestsComponent} from "./client/client-requests/client.requests.component";
import {ClientRequestComponent} from "./client/client-request/client.request.component";
import {ClientTransactionsComponent} from "./client/client-transactions/client.transactions.component";
import {ClientTransactionComponent} from "./client/client-transaction/client.transaction.component";
import {ClientAssetsComponent} from "./client/client-assets/client.assets.component";
import {ClientAssetComponent} from "./client/client-asset/client.asset.component";
import {ClientStocksComponent} from "./client/client-stocks/client.stocks.component";
import {ClientStockComponent} from "./client/client-stock/client.stock.component";
import {ClientService} from "./services/client.service";
import {AdminService} from "./services/admin.service";
import {BrokerService} from "./services/broker.service";

@NgModule({
    declarations: [
        AppComponent,
        SettingsComponent,
        HomeComponent,
        SignInComponent,
        SignUpComponent,
        ClientBaseComponent,
        ClientInfoComponent,
        ClientPassportComponent,
        ClientBrokerageAccountComponent,
        ClientBrokerageAccountInfoComponent,
        ClientAgreementComponent,
        ClientAgreementInfoComponent,
        ClientAgreementExtendComponent,
        ClientPutMoneyComponent,
        ClientExchangeComponent,
        ClientExchangeMoneyToStocksComponent,
        ClientExchangeStocksToMoneyComponent,
        ClientRequestsComponent,
        ClientRequestComponent,
        ClientTransactionsComponent,
        ClientTransactionComponent,
        ClientAssetsComponent,
        ClientAssetComponent,
        ClientStocksComponent,
        ClientStockComponent
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
        AuthService,
        AdminService,
        BrokerService,
        ClientService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
