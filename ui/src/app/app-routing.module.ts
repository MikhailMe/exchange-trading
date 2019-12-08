import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
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
import {BrokerBaseComponent} from "./broker/broker-base/broker.base.component";
import {BrokerInfoComponent} from "./broker/broker-info/broker.info.component";
import {BrokerRequestsComponent} from "./broker/broker-requests/broker.requests.component";
import {BrokerRequestComponent} from "./broker/broker-request/broker.request.component";
import {BrokerAgreementsComponent} from "./broker/broker-agreements/broker.agreements.component";
import {BrokerAgreementComponent} from "./broker/broker-agreement/broker.agreement.component";
import {AdminBaseComponent} from "./admin/admin-base/admin.base.component";
import {AdminInfoComponent} from "./admin/admin-info/admin.info.component";
import {AdminBrokersComponent} from "./admin/admin-brokers/admin.brokers.component";
import {AdminRequestsComponent} from "./admin/admin-requests/admin.requests.component";
import {AdminRequestComponent} from "./admin/admin-request/admin.request.component";
import {AdminRatesComponent} from "./admin/admin-rates/admin.rates.component";
import {AdminBankComponent} from "./admin/admin-bank/admin.bank.component";

const routes: Routes = [
    {path: '', component: HomeComponent},
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
    {path: 'broker/base', component: BrokerBaseComponent},
    {path: 'broker/info', component: BrokerInfoComponent},
    {path: 'broker/requests', component: BrokerRequestsComponent},
    {path: 'broker/request/:id', component: BrokerRequestComponent},
    {path: 'broker/agreements', component: BrokerAgreementsComponent},
    {path: 'broker/agreement/:id', component: BrokerAgreementComponent},
    {path: 'admin/base', component: AdminBaseComponent},
    {path: 'admin/info', component: AdminInfoComponent},
    {path: 'admin/brokers', component: AdminBrokersComponent},
    {path: 'admin/requests', component: AdminRequestsComponent},
    {path: 'admin/request/:id', component: AdminRequestComponent},
    {path: 'admin/rates', component: AdminRatesComponent},
    {path: 'admin/bank', component: AdminBankComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
