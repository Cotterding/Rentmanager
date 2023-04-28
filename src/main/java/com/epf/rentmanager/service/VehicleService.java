package com.epf.rentmanager.service;

import java.util.List;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;
import com.epf.rentmanager.constraint.VehicleConstraints;

@Service
public class VehicleService {

	private static VehicleDao vehicleDao;
	public static VehicleService instance;


	public VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public long create(Vehicle vehicle) throws ServiceException {
		try {
			if (vehicle.getConstructeur().isEmpty()) {
				throw new ServiceException("Le constructeur est vide");
				}
			if (vehicle.getNb_places()<1) {
				throw new ServiceException("Le nombre de place est inférieur à 1");
			}
			if (VehicleConstraints.isNbPlacesBetween(vehicle) == false) {
				throw new ServiceException("Le nombre de place n'est pas compris entre 2 et 9");
			}
			return vehicleDao.create(vehicle);
		} catch (DaoException e) {
			throw new ServiceException("Problème lors de la création du véhicule ");
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		try {
			return vehicleDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException("Problème lors de la création du véhicule ");
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Problème lors de la création du véhicule ");
		}
	}

	public static int count() throws ServiceException {
		try {
			return vehicleDao.count();
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors du comptage des véhicules");
		}
	}

	public void delete(long id) throws ServiceException {
		try {
			vehicleDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors de la suppression du vehicule");
		}
	}

	public void update(Vehicle vehicle) throws ServiceException {
		try {
			if (vehicle.getConstructeur().length() < 1) {
				throw new ServiceException("Le constructeur doit contenir au moins 1 caractère");
			}
			if (vehicle.getNb_places() < 1) {
				throw new ServiceException("La voiture doit contenir au moins 1 place");
			}
			vehicleDao.update(vehicle);
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors de la mise à jour du vehicule");
		}
	}

	public int countVehicleByClientId(long clientId) throws ServiceException {
		try {
			return vehicleDao.countVehicleByClientId(clientId);
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors du comptage des véhicules");
		}
	}

}
