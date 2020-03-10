package com.cit.its.shipping.front.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public static Result success() {
        return success(null);
    }

    public static<T> Result success(T data) {
        return new Result(0, null, data);
    }


    public static Result fail(Integer code, String message) {
        return new Result(code, message, null);
    }
}
