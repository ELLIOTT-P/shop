package com.example.shop.entity;

import lombok.ToString;
import org.springframework.lang.Nullable;

@ToString
public enum ResultStatus {
    /***
     * 返回响应枚举
     */
    OK(200, "请求处理成功"),

    BAD_REQUEST(400, "错误的请求"),

    UNAUTHORIZED(401, "当前请求未认证"),

    PASSWORD_ERROR(402, "用户名或密码错误"),

    NOT_FOUND(404, "资源不存在，API路径不对，或者无效路径"),

    REQUEST_TIMEOUT(408, "请求超时"),

    PARAMETER_ERROR(415, "参数错误"),

    NOTFOUNDTOKEN(416, "fa_token缺失"),

    NOPERMESSION(417, "没有接口请求权限"),

    SERVER_ERROR(500, "服务异常");

    /**
     * 状态码
     */
    private final int value;

    /**
     * 描述
     */
    private final String describe;

    ResultStatus(int value, String reason) {
        this.value = value;
        this.describe = reason;
    }

    /**
     * 获取状态码
     */
    public int value() {
        return this.value;
    }

    /**
     * 获取描述
     */
    public String getDescribe() {
        return this.describe;
    }

    /**
     * 根据状态码查找对象
     *
     * @param statusCode 状态码
     * @return ResultStatus
     * @throws IllegalArgumentException
     */
    public static ResultStatus valueOf(int statusCode) {
        ResultStatus status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("状态码不能为空");
        }
        return status;
    }

    /**
     * 根据状态码查找对象
     *
     * @param statusCode 状态码
     * @return ResultStatus
     */
    @Nullable
    public static ResultStatus resolve(int statusCode) {
        for (ResultStatus status : values()) {
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }
}
