package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;
import com.epf.rentmanager.constraint.ClientConstraints;

@Service
public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;

	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public long create(Client client) throws ServiceException {
		try {
			if (client.getNom().isEmpty()) {
				throw new ServiceException("Le nom est vide");
				}
			if (client.getPrenom().isEmpty()) {
				throw new ServiceException("Le prénom est vide");
			}
			if (ClientConstraints.isAdult(client) == false) {
				throw new ServiceException("Le client n'est pas majeur");
			}
			if (ClientConstraints.isFirstNameValid(client) == false) {
				throw new ServiceException("Le prénom doit contenir au moins 3 caractères");
			}
			if (ClientConstraints.isNameValid(client) == false) {
				throw new ServiceException("Le nom doit contenir au moins 3 caractères");
			}
			/*ClientConstraints constraints = new ClientConstraints();
			if (constraints.isEmailNotUnique(client)) {
				throw new ServiceException("L'email est déjà utilisé");
			}*/
			return clientDao.create(client);
		} catch (Exception e) {
			throw new ServiceException("Problème lors de la création du client ");
		}

	}

	public Client findById(long id) throws ServiceException {

		if (id<=0) {
			throw  new ServiceException("L'id est inférieur ou égal à 0");
		}
		try {
			return this.clientDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException("Problème lors de la création du client ");
		}
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("Problème lors de la création du client ");
		}
	}

	public int count() throws ServiceException {
		try {
			return clientDao.count();
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors du comptage des clients");
		}
	}

	public void delete(long id) throws ServiceException {
		try {
			clientDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors de la suppression du client");
		}
	}

	public void update(Client client) throws ServiceException {
		try {
			if (client.getNom().length() < 1) {
				throw new ServiceException("Le nom doit contenir au moins 1 caractère");
			}
			if (client.getPrenom().length() < 1) {
				throw new ServiceException("Le prénom doit contenir au moins 1 caractère");
			}
			clientDao.update(client);
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors de la mise à jour du client");
		}
	}

	public int countClientByVehicleId(long vehicleId) throws ServiceException {
		try {
			return clientDao.countClientByVehicleId(vehicleId);
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors du comptage des clients");
		}
	}
}
