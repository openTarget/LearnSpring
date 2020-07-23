package com.imooc.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * 〈一句话功能简述>
 * 〈〉
 *
 * @Author lhn
 * @Create 2020/7/15
 * @Since 1.0.0
 */
@Data
public class HeadLine {

    private Long lineId;
    private String lineName;
    private String lineLink;
    private String lineImg;
    private Integer priority;
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
    
    

}