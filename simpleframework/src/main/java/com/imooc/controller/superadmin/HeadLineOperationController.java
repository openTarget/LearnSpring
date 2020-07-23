package com.imooc.controller.superadmin;

import com.imooc.entity.bo.HeadLine;
import com.imooc.entity.dto.Result;
import com.imooc.service.solo.HeadLineService;

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
public class HeadLineOperationController {

    private HeadLineService headLineService;

    public Result <Boolean> addHeadLine(HttpServletRequest request, HttpServletResponse response){
        return headLineService.addHeadLine(new HeadLine());
    };

    public Result <Boolean> removeHeadLine(HttpServletRequest request, HttpServletResponse response){
        return headLineService.removeHeadLine(1);
    };

    public Result <Boolean> modifyHeadLine(HttpServletRequest request, HttpServletResponse response) {
        return headLineService.modifyHeadLine(new HeadLine());
    }

    public Result <HeadLine> queryHeadLineById(HttpServletRequest request, HttpServletResponse response) {
        return headLineService.queryHeadLineById(1);
    }

    public Result <List <HeadLine>> queryHeadLine(HttpServletRequest request, HttpServletResponse response) {
        return headLineService.queryHeadLine(null, 1, 100);
    }

}