package com.imooc.entity.bo;

import java.util.Date;

/**
 * 〈一句话功能简述>
 * 〈〉
 *
 * @Author lhn
 * @Create 2020/7/15
 * @Since 1.0.0
 */
public class ShopCategory {

    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private ShopCategory parent;
}