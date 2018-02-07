package com.forward.exchange.webservices.suza.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forward.exchange.webservices.suza.api.*;

import com.forward.exchange.webservices.suza.model.CreateComment;
import com.forward.exchange.webservices.suza.model.CreateRequest;
import com.forward.exchange.webservices.suza.model.GetLastComments;
import com.forward.exchange.webservices.suza.model.GetRequestComments;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.container.ContainerRequestContext;

public class SuzaApiServiceImpl extends SuzaApiService {
    @Override
    public Response createComment(String createComment, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        return baseClass.execute(createComment, CreateComment.class, "CreateComment");
    }

    @Override
    public Response createRequest(String createRequest, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        // TODO
        return baseClass.execute(createRequest, CreateRequest.class, "CreateRequest");
    }

    @Override
    public Response getLastComments(String getLastComments, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        // TODO
        return baseClass.execute(getLastComments, GetLastComments.class, "GetLastComments");
    }

    @Override
    public Response getRequestComments(String getRequestComments, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        // TODO
        return baseClass.execute(getRequestComments, GetRequestComments.class, "GetRequestComments");
    }
}
