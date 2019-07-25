package com.jin.learn.exception;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局的异常处理 处理RuntimeException
 */
@Provider
public class GlobalErrorHandler implements ExceptionMapper<RuntimeException> {

    private static Map<String, Object> message = new HashMap<>();

    static{
        message.put("error", "some error accour");
    }

    @Override
    public Response toResponse(RuntimeException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
    }
}
