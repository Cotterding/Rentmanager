package com.epf.rentmanager.constraint;

import java.time.LocalDate;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConstraints {

    private ClientDao clientDao;

    public static boolean isAdult(Client client) {
        LocalDate now = LocalDate.now();
        LocalDate birthdate = client.getNaissance();
        return birthdate.plusYears(18).isBefore(now);
    }

    public static boolean isNameValid(Client client) {
        return client.getNom().length() > 3;
    }

    public static boolean isFirstNameValid(Client client) {
        return client.getPrenom().length() > 3;
    }

    public boolean isEmailUnique(Client client) {
        return clientDao.doesClientAlreadyExist(client);
    }
}
