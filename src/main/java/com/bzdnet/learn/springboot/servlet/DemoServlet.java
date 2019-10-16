package com.bzdnet.learn.springboot.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "demoServlet",urlPatterns = "/demo/*")
public class DemoServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DemoServlet.class);

    @Override
    public void init() throws ServletException {
        log.info("Init DemoServlet!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(req.getRequestURI());
    }

    @Override
    public void destroy() {
        log.info("Destroy DemoServlet!");
    }
}
