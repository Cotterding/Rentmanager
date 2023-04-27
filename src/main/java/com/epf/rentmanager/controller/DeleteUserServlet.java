package com.epf.rentmanager.controller;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/delete")
public class DeleteUserServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long id = Integer.parseInt(request.getParameter("id"));
            clientService.delete(id);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/users");
    }
}
