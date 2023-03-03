package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		try {
			if (vehicle.getConstructeur().isEmpty()) {
				throw new ServiceException("Le constructeur est vide");
				}
			if (vehicle.getNb_places()<1) {
				throw new ServiceException("Le nombre de place est inférieur à 1");
			}
			return vehicleDao.create(vehicle);
		} catch (DaoException e) {
			throw new ServiceException("Problème lors de la création du véhicule ");
		}
		// TODO: créer un véhicule
	}

	public Vehicle findById(long id) throws ServiceException {
		try {
			return vehicleDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException("Problème lors de la création du véhicule ");
		}
		// TODO: récupérer un véhicule par son id
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicleDao.getInstance().findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Problème lors de la création du véhicule ");
		}
	}
	// TODO: récupérer tous les clients
}
