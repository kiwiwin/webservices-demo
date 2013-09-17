package org.kiwi.jaxws;

import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HelloWorldServiceTest {
    @Test
    public void should_return_hello_message() throws MalformedURLException {
        URL url = new URL("http://localhost:9999/ws/hello");

        QName qname = new QName("http://jaxws.kiwi.org/", "HelloWorldImplService");

        Service service = Service.create(url, qname);

        HelloWorld hello = service.getPort(HelloWorld.class);

        assertThat(hello.sayHello("kiwi"), is("hello kiwi"));
    }
}
