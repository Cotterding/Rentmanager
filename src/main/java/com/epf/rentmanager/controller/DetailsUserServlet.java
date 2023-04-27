package com.epf.rentmanager.controller;

import com.epf.rentmanager.exception.ServiceException;
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

@WebServlet("/users/details")
public class DetailsUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    VehicleService vehicleService;
    @Autowired
    ClientService clientService;
    @Autowired
    ReservationService reservationService;
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
            request.setAttribute("rentals", reservationService.findResaByClientId(id));
            request.setAttribute("nbRentals", reservationService.countResaByClientId(id));
            request.setAttribute("nbVehicles", vehicleService.countVehicleByClientId(id));
            request.setAttribute("vehicles", vehicleService);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
    }

}
