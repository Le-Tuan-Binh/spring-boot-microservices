package com.example.main.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Generic wrapper for API responses")
public class RestResponse<T> {

    @Schema(description = "Indicates if the request was successful")
    private boolean success;

    @Schema(description = "HTTP status code of the response")
    private Integer code;

    @Schema(description = "Detailed message about the response")
    private String message;

    @Schema(description = "Response payload data")
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
