package com.forward.exchange.webservices.suza.newapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forward.exchange.webservices.suza.model.CreateRequest;
import com.forward.exchange.webservices.suza.model.CreateRequestResponse;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("newapi")
public class NewApi {
    @POST
    @Path("/CreateRequest")
    @Produces("application/json")
    @Consumes("application/json")
    @io.swagger.annotations.ApiOperation(value = "Веб-сервис предназначен для создания обращения", notes = "Веб-сервис предназначен для создания обращения", response = CreateRequestResponse.class, tags={ "SUZA", })
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = CreateRequestResponse.class),

            @io.swagger.annotations.ApiResponse(code = 500, message = "Invalid operation", response = CreateRequestResponse.class) })
    public Response createRequest(@ApiParam(value = "{'name': 'value'}", required = true) String createRequest, @Context ContainerRequestContext requestContext) {
        try {
            CreateRequest request = new ObjectMapper().readValue(createRequest, CreateRequest.class);
            String jsonResponse;
            jsonResponse = new ObjectMapper().writeValueAsString(getCreateRequestResponse());
//            jsonResponse = new ObjectMapper().writeValueAsString(request);
            return Response.ok().entity(jsonResponse).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return Response.status(500, "Error").build();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/CreateRequest")
    @Produces("application/json")
    public Response createResponse() {
        try {
            String jsonResponse = new ObjectMapper().writeValueAsString(getCreateRequest());
            return Response.ok().entity(jsonResponse).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        return Response.status(500, "Error").build();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    private CreateRequest getCreateRequest() {
        CreateRequest result = new CreateRequest();

        result.setFILE(new byte[] {0, 1});
        result.setNLEVEL(1);
        result.setNPRIORITY(2);
        result.setNTYPE(3);
        result.setVCIRCUIT("V_CIRCUIT");
        result.setVDESCRIPTION("V_DESCRIPTION");
        result.setVEMAIL("mail@example.com");
        result.setVEXTIDENT("V_EXT_IDENT");
        result.setVREQUESTOR("V_REQUESTOR");
        result.setVTITLE("V_TITLE");

        return result;
    }

    private CreateRequestResponse getCreateRequestResponse() {
        CreateRequestResponse result = new CreateRequestResponse();

        result.setMESSAGE("Message: OK!");
        result.setREQUESTNUMBER(1);
        result.setRESULTCODE(200);

        return result;
    }
}