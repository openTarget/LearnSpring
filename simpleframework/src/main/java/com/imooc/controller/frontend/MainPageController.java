package com.imooc.controller.frontend;

import com.imooc.entity.dto.MainPageInfoDTO;
import com.imooc.entity.dto.Result;
import com.imooc.service.combine.HeadLineShopCategoryCombineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述>
 * 〈〉
 *
 * @Author lhn
 * @Create 2020/7/16
 * @Since 1.0.0
 */
public class MainPageController {
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result <MainPageInfoDTO> getMainPageInfo(HttpServletRequest request, HttpServletResponse response) {
        return headLineShopCategoryCombineService.getMainPageInfo();
    }


}