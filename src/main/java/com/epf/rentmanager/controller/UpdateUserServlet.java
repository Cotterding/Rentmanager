package com.epf.rentmanager.controller;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/update")
public class UpdateUserServlet extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("user", clientService.findById(id));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            Client client = new Client();
            int id = Integer.parseInt(request.getParameter("id"));
            client.setId(id);
            client.setNom(request.getParameter("last_name"));
            client.setPrenom(request.getParameter("first_name"));
            client.setEmail(request.getParameter("email"));
            client.setNaissance(LocalDate.parse(request.getParameter("naissance")));
            clientService.update(client);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/users");
    }
}
