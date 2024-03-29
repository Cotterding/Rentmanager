package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {

	//private static ClientDao instance = null;
	private ClientDao() {}

	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS count FROM Client;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";
	private static final String COUNT_CLIENTS_BY_VEHICLE_QUERY = "SELECT COUNT(id) AS count FROM Client WHERE id IN (SELECT client_id FROM Reservation WHERE vehicle_id=?);";
	private static final String FIND_CLIENT_BY_MAIL_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE email=? AND id <> ?;";
	
	public long create(Client client) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, client.getNom().toUpperCase()); // nom en majuscule
			preparedStatement.setString(2, client.getPrenom());
			preparedStatement.setString(3, client.getEmail());
			preparedStatement.setDate(4, Date.valueOf(client.getNaissance()));
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next()) {
				return resultSet.getLong(1);
			}
			return 0;
		} catch (SQLException e) {
			throw new DaoException();
		}

	}

	public void delete(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_QUERY);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public Client findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENT_QUERY);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Client client = new Client(
						(int)id,
						resultSet.getString("nom"),
						resultSet.getString("prenom"),
						resultSet.getString("email"),
						resultSet.getDate("naissance").toLocalDate()
				);
				return client;
			}
			return null;

		} catch (Throwable e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<Client>();

		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_CLIENTS_QUERY);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");;
				String nom = resultSet.getString("nom").toUpperCase();
				String prenom = resultSet.getString("prenom");
				String email = resultSet.getString("email");
				LocalDate naissance = resultSet.getDate("naissance").toLocalDate();

				clients.add(new Client(id, nom, prenom, email, naissance));
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
		return clients;
	}

	public int count() throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(COUNT_CLIENTS_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("count");
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
		return 0;
	}

	public void update(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_QUERY);
			preparedStatement.setString(1, client.getNom().toUpperCase());
			preparedStatement.setString(2, client.getPrenom());
			preparedStatement.setString(3, client.getEmail());
			preparedStatement.setDate(4, Date.valueOf(client.getNaissance()));
			preparedStatement.setLong(5, client.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public int countClientByVehicleId(long vehicleId) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(COUNT_CLIENTS_BY_VEHICLE_QUERY);
			preparedStatement.setLong(1, vehicleId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("count");
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
		return 0;
	}

	public boolean doesClientAlreadyExist(Client client) {

		try (Connection connection = ConnectionManager.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement(FIND_CLIENT_BY_MAIL_QUERY);
			stmt.setString(1, client.getEmail());
			stmt.setInt(2, client.getId());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return true;
	}

}


