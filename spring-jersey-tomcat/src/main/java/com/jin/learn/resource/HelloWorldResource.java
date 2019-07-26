package com.jin.learn.resource;

import com.cisco.webex.apis.core.annotation.ApiValidator;
import com.jin.learn.request.RequestHello;
import com.jin.learn.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/test")
@RestController
public class HelloWorldResource {

    @Autowired
    private HelloService helloService;

    @GET
    @Produces("text/plain")
    public String Message() {
        return helloService.say();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public RequestHello getMessage(@Valid RequestHello requestHello) {
        requestHello.setName("zhangjin");
        return requestHello;
    }

    @POST()
    @Path("/error")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public RequestHello error(RequestHello requestHello) {
        int a = 1 / 0;
        return requestHello;
    }


}
