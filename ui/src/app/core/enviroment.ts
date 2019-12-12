const baseUrl = 'http://localhost:8080/api/';
const adminPrefix = baseUrl + 'admin/';
const brokerPrefix = baseUrl + 'broker/';
const clientPrefix = baseUrl + 'client/';
const systemPrefix = baseUrl + 'system/';

export function createEnv() {
    return {
        clientPrefix,
        getClientInfo: clientPrefix,
        getClientById: clientPrefix + 'get/:clientId',
        setClientPassport: 'setPassport',
        openBrokerageAccount: clientPrefix + 'openBrokerageAccount/:clientId',
        closeBrokerageAccount: clientPrefix + 'closeBrokerageAccount/:clientId',
        putMoneyToAccount: clientPrefix + ':clientId/putMoneyToAccount',
        makeBrokerAgreement: clientPrefix + ':clientId/makeBrokerAgreement',
        extendBrokerAgreement: clientPrefix + ':clientId/extendBrokerAgreement',
        breakBrokerAgreement: clientPrefix + ':clientId/breakBrokerAgreement',
        exchangeMoneyToStocks: clientPrefix + ':clientId/exchangeMoneyToStocks',
        exchangeStocksToMoney: clientPrefix + ':clientId/exchangeStocksToMoney',
        getClientTransactions: clientPrefix + ':clientId/getTransactions',
        getClientTransactionById: clientPrefix + ':clientId/transaction/:id',
        getClientRequests: clientPrefix + ':clientId/getRequests',
        getClientRequestById: clientPrefix + ':clientId/request/:id',
        getClientAssets: clientPrefix + ':clientId/getAssets',
        getClientAssetById: clientPrefix + ':clientId/asset/:id',
        getClientStocks: clientPrefix + ':clientId/getStocks',
        getClientStockById: clientPrefix + ':clientId/stock/:id',

        getBroker: brokerPrefix,
        getBrokerById: brokerPrefix + 'get/:brokerId',
        checkBrokerRequests: brokerPrefix + ':brokerId/checkRequests',
        validateClientRequest: brokerPrefix + ':brokerId/validateClientRequest',
        approveClientRequest: brokerPrefix + ':brokerId/approveClientRequest',
        declineClientRequest: brokerPrefix + ':brokerId/declineClientRequest',

        getAdmin: adminPrefix,
        getAdminById: adminPrefix + 'get/:adminId',
        getAdminBrokers: adminPrefix + ':adminId/getBrokers',
        checkAdminRequests: adminPrefix + ':adminId/checkRequests',
        approveRequest: adminPrefix + ':adminId/approveRequest',
        declineRequest: adminPrefix + 'declineRequest/:clientRequestId',
        getRates: adminPrefix + 'getRates',
        getBankAssets: adminPrefix + 'getBankAssets',

        signIn: systemPrefix + 'signIn',
        signUp: systemPrefix + 'signUp',
        signOut: systemPrefix + 'signOut',
    };
}

export let environment = createEnv();
