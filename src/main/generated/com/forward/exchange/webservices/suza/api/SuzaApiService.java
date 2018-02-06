package com.forward.exchange.webservices.suza.api;

import com.forward.exchange.webservices.suza.api.*;
import com.forward.exchange.webservices.suza.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

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

import javax.ws.rs.core.Response;
import javax.ws.rs.container.ContainerRequestContext;
import javax.validation.constraints.*;

public abstract class SuzaApiService {
    public abstract Response createComment(String createComment,ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass);
    public abstract Response createRequest(String createRequest,ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass);
    public abstract Response getLastComments(String getLastComments,ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass);
    public abstract Response getRequestComments(String getRequestComments,ContainerRequestContext requestContext, com.forward.exchange.webservices.kit.DatabaseExchange baseClass);
}
