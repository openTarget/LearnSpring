package com.imooc.service.solo.impl;

import com.imooc.entity.bo.ShopCategory;
import com.imooc.entity.dto.Result;
import com.imooc.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * 〈一句话功能简述>
 * 〈〉
 *
 * @Author lhn
 * @Create 2020/7/16
 * @Since 1.0.0
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Override
    public Result <Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result <Boolean> removeShopCategory(int shopCategoryId) {
        return null;
    }

    @Override
    public Result <Boolean> modifyShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result <ShopCategory> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result <List <ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}