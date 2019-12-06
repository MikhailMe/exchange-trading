const baseUrl = 'http://localhost:8080/api/';

export function createEnv() {
    return {
        getClientInfo: baseUrl + 'client/',
        getClientRequests: baseUrl + 'client/requests/',
        openBrokerageAccount: baseUrl + 'client/open-brokerage-account',
        closeBrokerageAccount: baseUrl + 'client/close-brokerage-account',
        createBrokerAgreement: baseUrl + 'client/create-broker-agreement',
        extendBrokerAgreement: baseUrl + 'client/extend-broker-agreement',
        breakBrokerAgreement: baseUrl + 'client/break-broker-agreement',
        exchangeMoneyToStocks: baseUrl + 'client/exchange-m2s',
        exchangeStocksToMoney: baseUrl + 'client/exchange-s2m',

        getBroker: baseUrl + 'broker/',
        getBrokerRequests: baseUrl + 'broker/requests/',

        getAdmin: baseUrl + 'admin/',
        getAdminRequests: baseUrl + 'admin/requests/',

        signIn: baseUrl + 'system/signIn',
        signUp: baseUrl + 'system/signUp',
        signOut: baseUrl + 'system/signOut',
    };
}

export let environment = createEnv();
