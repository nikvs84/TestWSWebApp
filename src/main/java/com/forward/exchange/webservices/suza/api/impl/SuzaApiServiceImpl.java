package com.forward.exchange.webservices.suza.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forward.exchange.webservices.suza.api.*;

import com.forward.exchange.webservices.suza.model.CreateRequest;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.container.ContainerRequestContext;

public class SuzaApiServiceImpl extends SuzaApiService {
    @Override
    public Response createComment(String createComment, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        // TODO
        //return Response.serverError().entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Method is not implemented yet!")).build();
        return Response.serverError().entity("Method is not implemented yet!").build();
    }
    @Override
    public Response createRequest(String createRequest, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        // TODO
        //return Response.serverError().entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Method is not implemented yet!")).build();
        String methodName = "createRequest";
        try {
            CreateRequest createRequestObj = new ObjectMapper().readValue(createRequest, CreateRequest.class);
//            Response response = baseClass.processJsonRequest(createRequestObj, requestContext, methodName);
//            return response;
            String resp;
//            resp = baseClass.testRequestDB();
            resp = baseClass.testCallProcDB(createRequestObj, methodName);
            return Response.ok().entity(resp).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.serverError().entity("Method is not implemented yet!").build();
    }
    @Override
    public Response getLastComments(String getLastComments, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        // TODO
        //return Response.serverError().entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Method is not implemented yet!")).build();
        return Response.serverError().entity("Method is not implemented yet!").build();
    }
    @Override
    public Response getRequestComments(String getRequestComments, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        // TODO
        //return Response.serverError().entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Method is not implemented yet!")).build();
        return Response.serverError().entity("Method is not implemented yet!").build();
    }
}
