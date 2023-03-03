package com.epf.rentmanager.main;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//            System.out.println(ClientService.getInstance().findAll());
//            System.out.println(new Client(12, "Julie", "DOLIGEZ", "julie.doligez@gmail.com", LocalDate.now()));


        /*try  {
            System.out.println(VehicleService.getInstance().findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }*/
        final int MON_ID = 4;
        try  {
            System.out.println(ClientService.getInstance().findById(MON_ID));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
