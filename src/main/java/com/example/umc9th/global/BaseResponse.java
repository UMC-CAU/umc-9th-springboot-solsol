package com.example.umc9th.global;


import com.example.umc9th.global.response.code.BaseCode;
import com.example.umc9th.global.response.code.ReasonDTO;
import com.example.umc9th.global.response.status.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access= AccessLevel.PRIVATE) //이미 생성된 메서드만 사용하도록 함
@JsonPropertyOrder({"isSuccess","code","message","result"})
public class BaseResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    //성공 응답
    //가장 일반적인 성공 케이스(200 OK)를 위한 메서드
    public static <T> BaseResponse<T> onSuccess(T data){
        ReasonDTO reason = SuccessStatus._OK.getReason();
        return new BaseResponse<>(true, reason.getCode(), reason.getMessage(), data);
    }

    //201 CREATED 등 특정 성공 코드를 위한 메서드(of 메서드 대신 사용)
    public static <T> BaseResponse<T> onSuccess(BaseCode code, T data){
        ReasonDTO reason = code.getReason();
        return new BaseResponse<>(true, reason.getCode(), reason.getMessage(), data);
    }

    //실패 응답
    public static <T> BaseResponse<T> onFailure(String code, String message, T data){
        return new BaseResponse<>(false, code, message, data);
    }
}