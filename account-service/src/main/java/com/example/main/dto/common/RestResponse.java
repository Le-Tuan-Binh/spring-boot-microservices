package com.example.main.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {

    private boolean success;

    private Integer code;

    private String message;

    private T data;

    public static <T> RestResponse<T> success(String message, T data) {
        return new RestResponse<>(true, 200, message, data);
    }

    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<>(true, 200, null, data);
    }

    public static <T> RestResponse<T> error(int code, String message) {
        return new RestResponse<>(false, code, message, null);
    }

    public static <T> RestResponse<T> error(int code, String message, T data) {
        return new RestResponse<>(false, code, message, data);
    }

}
