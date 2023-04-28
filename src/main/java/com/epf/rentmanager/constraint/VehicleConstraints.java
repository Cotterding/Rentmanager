package com.epf.rentmanager.constraint;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Component;
@Component
public class VehicleConstraints {
    public static boolean isNbPlacesBetween(Vehicle vehicle) {
        return vehicle.getNb_places() > 2 && vehicle.getNb_places() < 9;
    }
}
