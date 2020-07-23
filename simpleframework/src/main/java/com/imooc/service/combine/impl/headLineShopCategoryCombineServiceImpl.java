package com.imooc.service.combine.impl;

import com.imooc.entity.bo.HeadLine;
import com.imooc.entity.bo.ShopCategory;
import com.imooc.entity.dto.MainPageInfoDTO;
import com.imooc.entity.dto.Result;
import com.imooc.service.combine.HeadLineShopCategoryCombineService;
import com.imooc.service.solo.HeadLineService;
import com.imooc.service.solo.ShopCategoryService;

import java.util.List;

/**
 * 〈一句话功能简述>
 * 〈〉
 *
 * @Author lhn
 * @Create 2020/7/16
 * @Since 1.0.0
 */
public class headLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {

    private HeadLineService headLineService;

    private ShopCategoryService shopCategoryService;


    @Override
    public Result <MainPageInfoDTO> getMainPageInfo() {
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(1);
        Result <List <HeadLine>> queryHeadLine = headLineService.queryHeadLine(headLine, 1, 4);
        ShopCategory shopCategory = new ShopCategory();
        Result <List <ShopCategory>> listResult = shopCategoryService.queryShopCategory(shopCategory, 1, 100);
        Result<MainPageInfoDTO> result = mergeMainPageInfoResult(queryHeadLine, listResult);
        return null;
    }

    private Result <MainPageInfoDTO> mergeMainPageInfoResult(Result <List <HeadLine>> queryHeadLine, Result <List <ShopCategory>> listResult) {
        return null;
    }
}