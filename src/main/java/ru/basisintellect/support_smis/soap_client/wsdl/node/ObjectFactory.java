package ru.basisintellect.support_smis.soap_client.wsdl.node;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }


    TestRequest createTestReqest() {
        return new TestRequest();
    }

    TestResponse createTestResponse() {
        return new TestResponse();
    }
}
