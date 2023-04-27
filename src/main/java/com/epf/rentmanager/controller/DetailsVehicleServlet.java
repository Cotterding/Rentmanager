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

@WebServlet("/vehicles/details")
public class DetailsVehicleServlet extends HttpServlet{
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
            request.setAttribute("vehicle", vehicleService.findById(id));
            request.setAttribute("rentals", reservationService.findResaByVehicleId(id));
            request.setAttribute("nbRentals", reservationService.countResaByVehicleId(id));
            request.setAttribute("nbClients", clientService.countClientByVehicleId(id));
            request.setAttribute("clients", clientService);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp").forward(request, response);
    }
}
