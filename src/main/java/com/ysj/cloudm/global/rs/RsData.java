package com.ysj.cloudm.global.rs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class RsData<T> {
    private final String resultCode;
    private final String msg;
    private T data;
}