package org.kiwi.jaxws;

import javax.jws.WebService;

@WebService(endpointInterface = "org.kiwi.jaxws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
