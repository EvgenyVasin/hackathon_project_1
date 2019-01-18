package ru.basisintellect.support_smis.soap_client.wsdl.node;

import ru.basisintellect.support_smis.soap_client.wsdl.NamespaceConstants;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Вологодский Е.В.
 * @date 01.09.2010
 * @company ОАО "ICL-КПО ВС"
 */
@XmlType(namespace = NamespaceConstants.NODE)
@XmlRootElement(name = "TestResponse")
public class TestResponse {

}
