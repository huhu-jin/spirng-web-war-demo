package com.jin.learn;

import com.jin.learn.bean.Company;
import com.jin.learn.bean.Employee;
import com.jin.learn.bean.handeler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DispatcherServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger("aaa");


    private WebApplicationContext webApplicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = (WebApplicationContext) config.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        webApplicationContext.getBean(handeler.class);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = String.valueOf(req.getRequestURL());
        if (url.endsWith("company")) {
            Company company = (Company) this.webApplicationContext.getBean("company");
            company.doGet();
        } else if (url.endsWith("employee")) {
            Employee employee = (Employee) this.webApplicationContext.getBean("employee");
            employee.doGet();
        }
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println("hello world");
    }

}


