package com.amar.hello2.exception;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Office on 2014/6/27.
 */
public class MyRetrofitExceptionHandler implements ErrorHandler
{
    @Override public Throwable handleError(RetrofitError cause) {
        Response r = cause.getResponse();
        if (r != null && r.getStatus() == 401) {
            return new Exception(cause);
        }
        return cause;
    }
}
