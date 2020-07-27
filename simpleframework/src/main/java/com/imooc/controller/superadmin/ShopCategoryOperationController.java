package com.imooc.controller.superadmin;

import com.imooc.entity.bo.ShopCategory;
import com.imooc.entity.dto.Result;
import com.imooc.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 〈一句话功能简述>
 * 〈〉
 *
 * @Author lhn
 * @Create 2020/7/16
 * @Since 1.0.0
 */
@Controller
public class ShopCategoryOperationController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    Result <Boolean> addShopCategory(HttpServletRequest request, HttpServletResponse response) {
        return shopCategoryService.addShopCategory(new ShopCategory());
    }

    Result <Boolean> removeShopCategory(HttpServletRequest request, HttpServletResponse response) {
        return shopCategoryService.removeShopCategory(1);
    }

    Result <Boolean> modifyShopCategory(HttpServletRequest request, HttpServletResponse response) {
        return shopCategoryService.modifyShopCategory(new ShopCategory());
    }

    Result <ShopCategory> queryShopCategoryById(HttpServletRequest request, HttpServletResponse response) {
        return shopCategoryService.queryShopCategoryById(1);
    }

    Result <List <ShopCategory>> queryShopCategory(HttpServletRequest request, HttpServletResponse response) {
        return shopCategoryService.queryShopCategory(null, 1, 100);
    }

}