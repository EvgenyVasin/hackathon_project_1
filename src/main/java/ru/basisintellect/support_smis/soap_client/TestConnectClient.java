package ru.basisintellect.support_smis.soap_client;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import ru.basisintellect.support_smis.soap_client.wsdl.Action;
import ru.basisintellect.support_smis.soap_client.wsdl.NamespaceConstants;
import ru.basisintellect.support_smis.soap_client.wsdl.node.TestRequest;
import ru.basisintellect.support_smis.soap_client.wsdl.node.TestResponse;

import javax.xml.transform.TransformerException;
import java.io.IOException;



public class TestConnectClient extends WebServiceGatewaySupport {

    public final class ClientMessageCallBack
            implements WebServiceMessageCallback {

        /**the soapAction to be appended to the soap message.*/
        private String soapAction;

        /**constructor.
         * @param action the soapAction to be set.*/
        public ClientMessageCallBack(final String action) {
            this.soapAction = action;
        }

        @Override
        public void doWithMessage(final WebServiceMessage message)
                throws IOException, TransformerException {

            if (message instanceof SoapMessage) {
                SoapMessage soapMessage = (SoapMessage) message;
                soapMessage.setSoapAction(soapAction);
            }

        }
    }

    public TestConnectClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.basisintellect.support_smis.soap_client.wsdl.node");
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);

    }

    public TestResponse testConnect(TestRequest request){

        return (TestResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request, new ClientMessageCallBack(Action.TEST));

    }

}
