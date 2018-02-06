package com.forward.exchange.webservices.suza.api.impl;

import com.forward.exchange.webservices.suza.api.*;
import com.forward.exchange.webservices.suza.model.*;

import com.forward.exchange.webservices.suza.model.CreateComment;
import com.forward.exchange.webservices.suza.model.CreateCommentResponse;
import com.forward.exchange.webservices.suza.model.CreateRequest;
import com.forward.exchange.webservices.suza.model.CreateRequestResponse;
import com.forward.exchange.webservices.suza.model.GetLastComments;
import com.forward.exchange.webservices.suza.model.GetLastCommentsResponse;
import com.forward.exchange.webservices.suza.model.GetRequestComments;
import com.forward.exchange.webservices.suza.model.GetRequestCommentsResponse;

import java.util.List;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.container.ContainerRequestContext;
import javax.validation.constraints.*;

public class SuzaApiServiceImpl extends SuzaApiService {
    @Override
    public Response createComment(CreateComment createComment, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        // TODO
        //return Response.serverError().entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Method is not implemented yet!")).build();
        return Response.serverError().entity("Method is not implemented yet!").build();
    }
    @Override
    public Response createRequest(CreateRequest createRequest, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        // TODO
        //return Response.serverError().entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Method is not implemented yet!")).build();
        return Response.serverError().entity("Method is not implemented yet!").build();
    }
    @Override
    public Response getLastComments(GetLastComments getLastComments, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        // TODO
        //return Response.serverError().entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Method is not implemented yet!")).build();
        return Response.serverError().entity("Method is not implemented yet!").build();
    }
    @Override
    public Response getRequestComments(GetRequestComments getRequestComments, ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass) {
        // TODO
        //return Response.serverError().entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Method is not implemented yet!")).build();
        return Response.serverError().entity("Method is not implemented yet!").build();
    }
}
