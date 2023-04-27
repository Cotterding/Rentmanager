package com.epf.rentmanager.controller;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
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

@WebServlet("/vehicles/create")
public class CreateVehicleServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        // traitement du formulaire (appel à la méthode de sauvegarde)
        try {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(UUID.randomUUID().hashCode());
            vehicle.setConstructeur(request.getParameter("manufacturer"));
            vehicle.setNb_places(Integer.parseInt(request.getParameter("seats")));
            vehicleService.create(vehicle);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/cars");
    }
}
