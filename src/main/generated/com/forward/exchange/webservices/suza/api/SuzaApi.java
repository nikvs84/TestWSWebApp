package com.forward.exchange.webservices.suza.api;

import com.forward.exchange.webservices.suza.model.*;
import com.forward.exchange.webservices.suza.api.SuzaApiService;
import com.forward.exchange.webservices.suza.api.factories.SuzaApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

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
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("suza")


@io.swagger.annotations.Api(description = "the suza API")

public class SuzaApi extends com.forward.exchange.webservices.kit.DatabaseExchange {
    private final SuzaApiService delegate = SuzaApiServiceFactory.getSuzaApi();

    @POST
    @Path("/CreateComment")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Веб-сервис предназначен для создания комментария по обращению", notes = "Веб-сервис предназначен для создания обращения", response = CreateCommentResponse.class, tags = {"SUZA",})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = CreateCommentResponse.class),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Invalid operation", response = CreateCommentResponse.class)})
    public Response createComment(@ApiParam(value = "", required = true) String createComment
            , @Context ContainerRequestContext requestContext) {
        return delegate.createComment(createComment, requestContext, this);
    }

    @POST
    @Path("/CreateRequest")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Веб-сервис предназначен для создания обращения", notes = "Веб-сервис предназначен для создания обращения", response = CreateRequestResponse.class, tags = {"SUZA",})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = CreateRequestResponse.class),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Invalid operation", response = CreateRequestResponse.class)})
    public Response createRequest(@ApiParam(value = "", required = true) String createRequest
            , @Context ContainerRequestContext requestContext) {
        return delegate.createRequest(createRequest, requestContext, this);
    }

    @POST
    @Path("/GetLastComments")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Веб-сервис предназначен для получения всех комментариев с указанной даты", notes = "Веб-сервис предназначен получения всех комментариев с указанной даты", response = GetLastCommentsResponse.class, tags = {"SUZA",})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = GetLastCommentsResponse.class),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Invalid operation", response = GetLastCommentsResponse.class)})
    public Response getLastComments(@ApiParam(value = "", required = true) String getLastComments
            , @Context ContainerRequestContext requestContext) {
        return delegate.getLastComments(getLastComments, requestContext, this);
    }

    @POST
    @Path("/GetRequestComments")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Веб-сервис предназначен для получения всех комментариев по обращению", notes = "Веб-сервис предназначен для получения всех комментариев по обращению", response = GetRequestCommentsResponse.class, tags = {"SUZA",})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = GetRequestCommentsResponse.class),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Invalid operation", response = GetRequestCommentsResponse.class)})
    public Response getRequestComments(@ApiParam(value = "", required = true) String getRequestComments
            , @Context ContainerRequestContext requestContext) {
        return delegate.getRequestComments(getRequestComments, requestContext, this);
    }
}
