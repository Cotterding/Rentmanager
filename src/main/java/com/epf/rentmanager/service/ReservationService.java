package com.epf.rentmanager.service;


import java.util.List;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;
import com.epf.rentmanager.constraint.ReservationConstraints;

@Service
public class ReservationService {
    private static ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public long create(Reservation reservation) throws ServiceException {
        try {
            if(reservation.getDebut().isAfter(reservation.getFin())){
                throw new ServiceException("La date de début doit être avant la date de fin");
            }
            if(ReservationConstraints.isReservedMoreThanSevenDaysBySameUser(reservation)){
                throw new ServiceException("Un utilisateur ne peut pas réserver plus de 7 jours");
            }
            /*List<Reservation> array = new ArrayList<>();
            array = reservationDao.findResaByVehicleId(reservation.getVehicle_id());
            if (ReservationConstraints.isVehicleReservedMoreThan30DaysWithoutPause(array)) {
                throw new ServiceException("Un véhicule ne peut pas être réservé plus de 30 jours sans pause");
            }
            if (ReservationConstraints.isAlreadyReservedToday(reservation,array)) {
                throw new ServiceException("Ce véhicule est déjà réservé aujourd'hui");
            }*/
            return reservationDao.create(reservation);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la création de la réservation");
        }
    }

    public List<Reservation> findResaByClientId(int client_id) throws ServiceException {
        if(client_id<0){
            throw new ServiceException("L'id doit être positif");
        }
        try {
            return reservationDao.findResaByClientId(client_id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur lors de la récupération des réservations");
        }
    }

    public List<Reservation> findResaByVehicleId(int vehicle_id) throws ServiceException {
        if(vehicle_id<0){
            throw new ServiceException("L'id doit être positif");
        }
        try {
            return reservationDao.findResaByVehicleId(vehicle_id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Erreur lors de la récupération des réservations");
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            return reservationDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la récupération des réservations");
        }
    }

    public static int count() throws ServiceException {
        try {
            return reservationDao.count();
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors du comptage des réservations");
        }
    }

    public void delete(long id) throws ServiceException {
        try {
            reservationDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la suppression de la réservation");
        }
    }

    public Reservation findById(long id) throws ServiceException {
        if(id<0){
            throw new ServiceException("L'id doit être positif");
        }
        try {
            return reservationDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la récupération de la réservation");
        }
    }

    public void update(Reservation reservation) throws ServiceException {
        try {
            reservationDao.update(reservation);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la mise à jour de la réservation");
        }
    }

    public int countResaByClientId(int client_id) throws ServiceException {
        try {
            return reservationDao.countResaByClientId(client_id);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors du comptage des réservations");
        }
    }

    public int countResaByVehicleId(int client_id) throws ServiceException {
        try {
            return reservationDao.countResaByVehicleId(client_id);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors du comptage des réservations");
        }
    }

}


