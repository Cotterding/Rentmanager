package com.epf.rentmanager.main;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws ServiceException {

        //CREATE CLIENT
        //System.out.println(new Client(12, "Julie", "DOLIGEZ", "julie.doligez@gmail.com", LocalDate.now()));

        //CREATE VEHICLE
        //System.out.println(new Vehicle(12, "Renault", "Clio", 3));

        //FIND ALL CLIENTS
        /*try  {
            System.out.println(VehicleService.getInstance().findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }*/

        //FIND CLIENT BY ID
        /*final int MON_ID = 4;
        try  {
            System.out.println(ClientService.getInstance().findById(MON_ID));
        } catch (ServiceException e) {
            e.printStackTrace();
        }*/

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);

        final int MON_ID = 4;
        try  {
            System.out.println(clientService.findById(MON_ID));
        } catch (ServiceException e) {
            e.printStackTrace();
    }
}}
