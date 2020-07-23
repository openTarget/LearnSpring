package com.imooc.controller;

import com.imooc.controller.frontend.MainPageController;
import com.imooc.controller.superadmin.HeadLineOperationController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
@WebServlet("/")
@Slf4j
public class DispatcherServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        log.info("request path is:{}", request.getServletPath());
        log.info("request method is:{}" + request.getMethod());
        if (request.getServletPath() == "/frontend/getmainpapeinfo" && request.getMethod() == " GET") {
            new MainPageController().getMainPageInfo(request, response);
        } else if (request.getServletPath() == "/superadmin/addheadline" && request.getMethod() == " GET") {
            new HeadLineOperationController().addHeadLine(request, response);
        }
    }

}