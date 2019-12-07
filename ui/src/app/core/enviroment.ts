const baseUrl = 'http://localhost:8080/api/';
const adminPrefix = 'admin/';
const brokerPrefix = 'broker/';
const clientPrefix = 'client/';
const systemPrefix = 'system/';

export function createEnv() {
    return {
        getClientInfo: baseUrl + clientPrefix,
        setClientPassport: baseUrl + clientPrefix + ':clientId/setPassport',
        openBrokerageAccount: baseUrl + clientPrefix + 'openBrokerageAccount/:clientId',
        closeBrokerageAccount: baseUrl + clientPrefix + 'closeBrokerageAccount/:clientId',
        putMoneyToAccount: baseUrl + clientPrefix + ':clientId/putMoneyToAccount',
        makeBrokerAgreement: baseUrl + clientPrefix + ':clientId/makeBrokerAgreement',
        extendBrokerAgreement: baseUrl + clientPrefix + ':clientId/extendBrokerAgreement',
        breakBrokerAgreement: baseUrl + clientPrefix + ':clientId/breakBrokerAgreement',
        exchangeMoneyToStocks: baseUrl + clientPrefix + ':clientId/exchangeMoneyToStocks',
        exchangeStocksToMoney: baseUrl + clientPrefix + ':clientId/exchangeStocksToMoney',
        getClientTransactions: baseUrl + clientPrefix + ':clientId/getTransactions',

        getBroker: baseUrl + brokerPrefix,
        checkBrokerRequests: baseUrl + brokerPrefix + ':brokerId/checkRequests',
        validateClientRequest: baseUrl + brokerPrefix + ':brokerId/validateClientRequest',
        approveClientRequest: baseUrl + brokerPrefix + ':brokerId/approveClientRequest',
        declineClientRequest: baseUrl + brokerPrefix + ':brokerId/declineClientRequest',

        getAdmin: baseUrl + adminPrefix,
        checkAdminRequests: baseUrl + adminPrefix +':adminId/checkRequests',
        approveRequest: baseUrl + adminPrefix +':adminId/approveRequest',
        declineRequest: baseUrl + adminPrefix +'declineRequest/:clientRequestId',
        getRates: baseUrl + adminPrefix +'getRates',
        getBankAssets: baseUrl + adminPrefix +'getBankAssets',

        signIn: baseUrl + systemPrefix + 'signIn',
        signUp: baseUrl + systemPrefix + 'signUp',
        signOut: baseUrl + systemPrefix + 'signOut',
    };
}

export let environment = createEnv();
