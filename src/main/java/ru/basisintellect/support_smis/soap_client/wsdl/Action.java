package ru.basisintellect.support_smis.soap_client.wsdl;

/**
 * @author Вологодский Е.В.
 * @date 09.09.2010
 * @company ОАО "ICL-КПО ВС"
 */
public interface Action {

    /**
     * Проверка наличия подключения
     */
    String TEST = "http://basis-edu.ru/monitoring/schemas/node/TestRequest";

    /**
     * Передача сообщения
     */
    String DISPATCH = "http://basis-edu.ru/monitoring/schemas/node/DispatchMessageRequest";

    /**
     * Регламентные работы
     */
    String MAINTENANCE = "http://basis-edu.ru/monitoring/schemas/node/DispatchMaintenanceRequest";

    /**
     * Контроль
     */
    String CONTROL_POINT = "http://basis-edu.ru/monitoring/schemas/node/DispatchControlPointRequest";
}
