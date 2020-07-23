package com.imooc;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述>
 * 〈〉
 *
 * @Author lhn
 * @Create 2020/7/14
 * @Since 1.0.0
 */
@WebServlet("/hello")
@Slf4j
public class HelloServlet extends HttpServlet {

    public void init() {
        System.out.println("初始化Servlet。。。");
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行doget方法");
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = "我的简易框架";
        log.info("123");
        request.setAttribute("name", name);
        request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(request, response);
    }

}