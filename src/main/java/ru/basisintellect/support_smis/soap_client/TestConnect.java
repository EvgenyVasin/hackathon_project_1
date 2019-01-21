package ru.basisintellect.support_smis.soap_client;

import org.springframework.ws.soap.client.SoapFaultClientException;
import ru.basisintellect.support_smis.soap_client.wsdl.node.TestRequest;
import ru.basisintellect.support_smis.soap_client.wsdl.node.TestResponse;

public class TestConnect {
    public static boolean checkStatus(String url) {
        //"http://10.1.103.34:8080/monitoring/node/dispatch"
        try {
            TestConnectClient client = new TestConnectClient();
            client.setDefaultUri(url);
            TestRequest request = new TestRequest();
            TestResponse response = client.testConnect(request);
            System.out.println(response);
        } catch (SoapFaultClientException e) {
            //ловим наше исключение - комплекс работает
            return true;
        } catch (Exception e){
            //ловим любое другое исключение - не работает
            return false;
        }
        //что-то идет не так и ничего не ловим - не работает
        return false;
    }
}
