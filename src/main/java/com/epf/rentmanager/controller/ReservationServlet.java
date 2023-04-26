package com.epf.rentmanager.controller;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rents")
public class ReservationServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private ReservationService reservationService;
    @Autowired VehicleService vehicleService;
    @Autowired ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("rents",reservationService.findAll());
            request.setAttribute("vehicles", vehicleService);
            request.setAttribute("clients", clientService);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
    }
}

