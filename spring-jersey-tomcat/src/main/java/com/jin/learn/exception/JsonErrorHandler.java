package com.jin.learn.exception;

import org.codehaus.jackson.map.JsonMappingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class JsonErrorHandler implements ExceptionMapper<JsonMappingException> {
    private static Map<String, Object> message = new HashMap<>();

    static {
        message.put("error", "json parse fail");
    }

    @Override
    public Response toResponse(JsonMappingException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
    }
}
