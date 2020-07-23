package com.imooc.entity.dto;

import lombok.Data;

/**
 * 〈一句话功能简述>
 * 〈〉
 *
 * @Author lhn
 * @Create 2020/7/16
 * @Since 1.0.0
 */
@Data
public class Result<T> {

    /** 返回的状态码  200表示成功  */
    private int code;
    /** 本次请求结果的详情 */
    private String msg;
    /** 本次请求返回的结果集  */
    private T data;
}