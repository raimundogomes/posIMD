package com.imd030.sgr.utils;

public interface NetworkRequestCallback<T> {

    public void onRequestResponse(T response);
    public void onRequestError(Exception error);

}
