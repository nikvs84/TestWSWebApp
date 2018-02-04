package com.fwt.suza.api.factories;

import com.fwt.suza.api.SuzaApiService;
import com.fwt.suza.api.impl.SuzaApiServiceImpl;

public class SuzaApiServiceFactory {
    private final static SuzaApiService service = new SuzaApiServiceImpl();

    public static SuzaApiService getSuzaApi() {
        return service;
    }
}
