package org.kiwi.springws;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.xml.soap.MessageFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class HolidayEndpointTest {
    private WebServiceTemplate webServiceTemplate;
    private static String HELLO_MESSAGE = "<MessageRequest xmlns=\"http://springws.kiwi.org/schema/HolidayRequest\">go</MessageRequest>";

    private static String HOLIDAY_MESSAGE = "<HolidayRequest xmlns='http://springws.kiwi.org/schema/HolidayRequest'>\n" +
            "    <Holiday>\n" +
            "        <StartDate>2006-07-03</StartDate>\n" +
            "        <EndDate>2006-07-07</EndDate>\n" +
            "    </Holiday>\n" +
            "    <Employee>\n" +
            "        <Number>42</Number>\n" +
            "        <FirstName>Arjen</FirstName>\n" +
            "        <LastName>Poutsma</LastName>\n" +
            "    </Employee>\n" +
            "</HolidayRequest>";

    @Before
    public void setUp() throws Exception {
        webServiceTemplate = new WebServiceTemplate();

        MessageFactory messageFactory = MessageFactory.newInstance();
        WebServiceMessageFactory webServiceMessageFactory = new SaajSoapMessageFactory(messageFactory);

        webServiceTemplate.setMessageFactory(webServiceMessageFactory);
    }

    @Test
    public void should_access_hello_service() {
        String url = "http://localhost:8080/spring-ws/helloService";
        StreamSource source = new StreamSource(new StringReader(HELLO_MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(url, source, result);
    }

    @Test
    public void should_access_holiday_service() {
        String url = "http://localhost:8080/spring-ws/holidayService";
        StreamSource source = new StreamSource(new StringReader(HOLIDAY_MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(url, source, result);
    }
}
