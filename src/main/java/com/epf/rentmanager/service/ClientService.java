package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	/*private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}*/

	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	/*public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}*/
	
	
	public long create(Client client) throws ServiceException {
		try {
			if (client.getNom().isEmpty()) {
				throw new ServiceException("Le nom est vide");
				}
			if (client.getPrenom().isEmpty()) {
				throw new ServiceException("Le prénom est vide");
			}
			return clientDao.create(client);
		} catch (Exception e) {
			throw new ServiceException("Problème lors de la création du client ");
		}
		// Créer un client

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
		// Récupérer un client par son id
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("Problème lors de la création du client ");
		}
	}
	// Récupérer tous les clients

	public int count() throws ServiceException {
		try {
			return clientDao.count();
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors du comptage des clients");
		}
	}
}
