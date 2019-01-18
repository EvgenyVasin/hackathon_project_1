package ru.basisintellect.support_smis.soap_client;

import ru.basisintellect.support_smis.soap_client.wsdl.node.TestRequest;
import ru.basisintellect.support_smis.soap_client.wsdl.node.TestResponse;

public class TestConnect {
    public static void main(String[] args) {
        TestConnectClient client = new TestConnectClient();
        client.setDefaultUri("http://10.1.103.34:8080/monitoring/node/dispatch");
        TestRequest request = new TestRequest();
        TestResponse response = client.testConnect(request);
        System.out.println(response);
    }

}
