package com.imooc.entity.dto;

import com.imooc.entity.bo.HeadLine;
import com.imooc.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 * 〈一句话功能简述>
 * 〈〉
 *
 * @Author lhn
 * @Create 2020/7/16
 * @Since 1.0.0
 */
@Data
public class MainPageInfoDTO {

    private List <HeadLine> headLineList;
    private List <ShopCategory> shopCategoryList;

}