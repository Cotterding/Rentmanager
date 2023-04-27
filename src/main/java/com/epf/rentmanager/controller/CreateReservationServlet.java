package com.epf.rentmanager.controller;

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
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

@WebServlet("/rents/create")
public class CreateReservationServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Autowired
    ReservationService reservationService;
    @Autowired
    VehicleService vehicleService;
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
            request.setAttribute("vehicles", vehicleService.findAll());
            request.setAttribute("clients", clientService.findAll());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        try {
            Reservation reservation = new Reservation();
            reservation.setId(UUID.randomUUID().hashCode());
            reservation.setVehicle_id(Integer.parseInt(request.getParameter("car")));
            reservation.setClient_id(Integer.parseInt(request.getParameter("client")));
            reservation.setDebut(LocalDate.parse(request.getParameter("begin")));
            reservation.setFin(LocalDate.parse(request.getParameter("end")));
            //System.out.println(reservation);
            reservationService.create(reservation);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/rents");
    }
}
