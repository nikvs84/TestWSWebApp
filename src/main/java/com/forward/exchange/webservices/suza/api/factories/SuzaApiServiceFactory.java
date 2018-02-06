package com.forward.exchange.webservices.suza.api.factories;

import com.forward.exchange.webservices.suza.api.SuzaApiService;
import com.forward.exchange.webservices.suza.api.impl.SuzaApiServiceImpl;


public class SuzaApiServiceFactory {
    private final static SuzaApiService service = new SuzaApiServiceImpl();

    public static SuzaApiService getSuzaApi() {
        return service;
    }
}
