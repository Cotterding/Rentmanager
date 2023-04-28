package com.epf.rentmanager.constraint;

import com.epf.rentmanager.model.Reservation;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import com.epf.rentmanager.model.Reservation;
import java.util.List;
import java.time.temporal.ChronoUnit;
import static java.lang.Math.abs;

import org.springframework.stereotype.Component;
@Component
public class ReservationConsraints {

    public static boolean isAlreadyReservedToday(Reservation nouvelleReservation, List<Reservation> reservations) {

        Collections.sort(reservations, Comparator.comparing(Reservation::getDebut));

        for (int i = 0; i < reservations.size() - 1; i++) {
            Reservation currentReservation = reservations.get(i);
            Reservation nextReservation = reservations.get(i + 1);

            if (nouvelleReservation.getDebut().isBefore(currentReservation.getFin()) && nouvelleReservation.getFin().isAfter(nextReservation.getDebut())) {
                return true;
            }
        }
        return false;

    }

    public static boolean isReservedMoreThanSevenDaysBySameUser(Reservation reservation) {
        return abs(ChronoUnit.DAYS.between(reservation.getDebut(), reservation.getFin())) > 7;
    }

    public boolean isVehicleReservedMoreThan30DaysWithoutPause(List<Reservation> reservations) {
        Collections.sort(reservations, Comparator.comparing(Reservation::getDebut));

        LocalDate lastEnd = null;
        int count = 0;

        for (Reservation reservation : reservations) {
            if (lastEnd == null || !reservation.getDebut().isEqual(lastEnd.plusDays(1))) {
                count = 0;
            }
            int days = (int) ChronoUnit.DAYS.between(reservation.getDebut(), reservation.getFin()) + 1;
            count += days;

            if (count > 30) {
                return true;
            }
            lastEnd = reservation.getFin();
        }
        return false;
    }
}
