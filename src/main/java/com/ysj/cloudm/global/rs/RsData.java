package com.ysj.cloudm.global.rs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class RsData<T> {
    private final int statusCode;
    private final String msg;
    private T data;

    public static <T> RsData<T> of(int statusCode, String msg) {
        return of(statusCode, msg, null);
    }

    public static <T> RsData<T> of(int statusCode, String msg, T data) {
        return new RsData<>(statusCode, msg, data);
    }

    public boolean isSuccess() {
        return getStatusCode() >= 200 && getStatusCode() < 400;
    }
}