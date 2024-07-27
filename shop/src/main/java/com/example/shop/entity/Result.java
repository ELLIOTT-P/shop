package com.example.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.io.Serializable;

@ToString
public final class Result<T> implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    /***
     * 统一业务返回错误码
     */
    public static final int UNITE_ERRORCODE = 405;

    /**
     * 时间戳
     */
    @Getter
    @Setter
    private long timestamp;

    /**
     * 状态
     */
    @Getter
    @Setter
    private int code;

    /**
     * 描述
     */
    @Getter
    @Setter
    private String message;

    /**
     * 返回的数据
     */
    @Getter
    @Setter
    private T data;

    /**
     * 请求处理成功
     * 适用于无返回值的情况
     *
     * @return Result
     */
    public static Result succ() {
        return succ("");
    }

    public Result(int status, String message){
        this.code = status;
        this.message = message;
        setTime(this);
    }

    public Result(){}

    /**
     * 请求处理成功
     *
     * @param data 待返回的对象
     * @return Result
     */
    public static <T> Result<T> succ(T data) {
        Result<T> result = new Result<>();
        setTime(result);
        result.setCode(ResultStatus.OK.value());
        result.setMessage("请求处理成功");
        result.setData(data);
        return result;
    }

    /**
     * 请求处理失败
     *
     * @param code 状态码 {@link ResultStatus}
     * @return
     */
    public static <T> Result<T> error(int code) {
        return error(code, "");
    }


    /**
     * 请求处理失败
     *
     * @param code   状态码 {@link ResultStatus}
     * @param describe 描述
     * @param <T>
     * @return Result
     */
    public static <T> Result<T> error(int code, String describe) {
        Result<T> result = new Result<>();
        setTime(result);
        result.setCode(code);
        result.setMessage(describe);
        result.setData(null);
        return result;
    }


    public static <T> Result<T> error(String describe) {
        Result<T> result = new Result<>();
        setTime(result);
        result.setCode(UNITE_ERRORCODE);
        result.setMessage(describe);
        return result;
    }

    /**
     * 服务异常
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> serverError() {
        return serverError("");
    }

    /**
     * 服务异常
     *
     * @param describe 描述说明
     * @param <T>
     * @return
     */
    public static <T> Result<T> serverError(String describe) {
        return error(ResultStatus.SERVER_ERROR.value(), describe);
    }

    /**
     * 参数错误
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> parameterError() {
        return parameterError("");
    }

    /**
     * 参数错误
     *
     * @param describe 描述说明
     * @param <T>
     * @return
     */
    public static <T> Result<T> parameterError(String describe) {
        return error(ResultStatus.PARAMETER_ERROR.value(), describe);
    }

    private static void setTime(Result rs) {
        rs.setTimestamp(System.currentTimeMillis());
    }
}
