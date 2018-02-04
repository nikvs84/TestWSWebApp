package com.fwt.suza.api;

import com.fwt.suza.model.*;
import com.fwt.suza.api.SuzaApiService;
import com.fwt.suza.api.factories.SuzaApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import com.fwt.suza.model.CreateRequest;
import com.fwt.suza.model.CreateRequestResponse;

import java.util.List;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;
/*
@Path("/suza")
@io.swagger.annotations.Api(description = "the suza API")*/


public class SuzaApi /*extends com.forward.exchange.webservices.tcs.kit.TcsDatabaseExchange*/ {
    private final SuzaApiService delegate = SuzaApiServiceFactory.getSuzaApi();
/*
    @POST
    @Path("/CreateRequest")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Веб-сервис предназначен для создания обращения", notes = "Веб-сервис предназначен для создания обращения", response = CreateRequestResponse.class, tags={ "SUZA", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = CreateRequestResponse.class),

        @io.swagger.annotations.ApiResponse(code = 500, message = "Invalid operation", response = CreateRequestResponse.class) })
    public Response createRequest(@ApiParam(value = "" ,required=true) CreateRequest createRequest
,@Context ContainerRequestContext requestContext)
    {
        return delegate.createRequest(createRequest,requestContext, this);
    }
*/
}