/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.ws;

import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * @author elton.ballhysa
 */

@WebService(name = "HelloService")
public class HelloWS {
    
    @WebMethod(operationName = "hi")
    public @WebResult(name = "greeting") String simpleHello(@WebParam(name = "name") String name) {
        if (name == null || name.isEmpty())
            name = "World";
        
        return "Hello " + name;
    }
    
    @WebMethod(operationName = "complexHello")
    public @WebResult(name="HelloResponse") HelloResponse complexHello(@WebParam(name="HelloRequest") HelloRequest request) {
        if (nullOrEmpty(request.getName())) {
            HelloResponse errorResponse = new HelloResponse();
            errorResponse.setSuccess(false);
            Error error = new Error();
            error.setCode("ERR001");
            error.setExplanation("Name must be present!");
            errorResponse.setError(error);
            return errorResponse;
        }
        if (nullOrEmpty(request.getSurname())) {
            HelloResponse errorResponse = new HelloResponse();
            errorResponse.setSuccess(false);
            Error error = new Error();
            error.setCode("ERR002");
            error.setExplanation("Surame must be present!");
            errorResponse.setError(error);
            return errorResponse;
        }
        
        HelloResponse response = new HelloResponse();
        response.setGreeting("Hello " + request.getName() + " " + request.getSurname());
        response.setTimestamp(new Date());
        response.setSuccess(true);
        return response;
    }
    
    private boolean nullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
    
}
