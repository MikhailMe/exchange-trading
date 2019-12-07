import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SettingsComponent} from './settings/settings.component';
import {SignInComponent} from './auth/signin/sign.in.component';
import {SignUpComponent} from './auth/signup/sign.up.component';
import {ClientBaseComponent} from "./client/client-base/client.base.component";
import {ClientInfoComponent} from "./client/client-info/client.info.component";
import {HomeComponent} from "./home/home.component";
import {ClientPassportComponent} from "./client/client-passport/client.passport.component";
import {ClientBrokerageAccountComponent} from "./client/client-brokerage-account/client.brokerage.account.component";
import {ClientBrokerageAccountInfoComponent} from "./client/client-brokerage-account-info/client.brokerage.account.info.component";
import {ClientAgreementComponent} from "./client/client-argreement/client.agreement.component";
import {ClientAgreementInfoComponent} from "./client/client-agreement-info/client.agreement.info.component";
import {ClientAgreementExtendComponent} from "./client/client-agreement-extend/client.agreement.extend.component";
import {ClientPutMoneyComponent} from "./client/client-put-money/client.put.money.component";
import {ClientExchangeComponent} from "./client/client-exchange/client.exchange.component";
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

const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'setting', component: SettingsComponent},
    {path: 'signin', component: SignInComponent},
    {path: 'signup', component: SignUpComponent},
    {path: 'client/base', component: ClientBaseComponent},
    {path: 'client/info', component: ClientInfoComponent},
    {path: 'client/passport', component: ClientPassportComponent},
    {path: 'client/brokerageAccount', component: ClientBrokerageAccountComponent},
    {path: 'client/brokerageAccount/info', component: ClientBrokerageAccountInfoComponent},
    {path: 'client/agreement', component: ClientAgreementComponent},
    {path: 'client/agreement/info', component: ClientAgreementInfoComponent},
    {path: 'client/agreement/extend', component: ClientAgreementExtendComponent},
    {path: 'client/putMoney', component: ClientPutMoneyComponent},
    {path: 'client/exchange', component: ClientExchangeComponent},
    {path: 'client/exchange/moneyToStocks', component: ClientExchangeMoneyToStocksComponent},
    {path: 'client/exchange/stocksToMoney', component: ClientExchangeStocksToMoneyComponent},
    {path: 'client/requests', component: ClientRequestsComponent},
    {path: 'client/request/:id', component: ClientRequestComponent},
    {path: 'client/transactions', component: ClientTransactionsComponent},
    {path: 'client/transaction/:id', component: ClientTransactionComponent},
    {path: 'client/assets', component: ClientAssetsComponent},
    {path: 'client/asset/:id', component: ClientAssetComponent},
    {path: 'client/stocks', component: ClientStocksComponent},
    {path: 'client/stock/:id', component: ClientStockComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
