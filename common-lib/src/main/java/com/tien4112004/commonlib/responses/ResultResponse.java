package com.tien4112004.commonlib.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultResponse<T> implements Serializable {
    private boolean success;
    private int statusCode;
    private String message;
    private long timestamp = System.currentTimeMillis();
    private T data;
}
