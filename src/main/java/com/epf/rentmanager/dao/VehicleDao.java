package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS count FROM Vehicle;";

	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur=?, nb_places=? WHERE id=?;";

	private static final String COUNT_VEHICLES_BY_CLIENT_QUERY = "SELECT COUNT(id) AS count FROM Vehicle WHERE id IN (SELECT vehicle_id FROM Reservation WHERE client_id=?);";

	
	public long create(Vehicle vehicle) throws DaoException {
		long id = 0;
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);) {

			preparedStatement.setString(1, vehicle.getConstructeur());
			preparedStatement.setInt(2, vehicle.getNb_places());

			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
		return id;
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEHICLE_QUERY);) {
			preparedStatement.setLong(1, vehicle.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		}
		return 0;
	}

	public Vehicle findById(long id) throws DaoException {
		Vehicle vehicle = new Vehicle();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				vehicle.setId((int) id);
				vehicle.setConstructeur(resultSet.getString("constructeur"));
				vehicle.setNb_places(resultSet.getInt("nb_places"));
			}
			return vehicle;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public List<Vehicle> findAll() throws DaoException {

		List<Vehicle> vehicles = new ArrayList<Vehicle>();

		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection. createStatement();
			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);

			while (rs.next()) {
				int id = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				int nb_places = rs.getInt("nb_places");

				vehicles.add(new Vehicle(id, constructeur, nb_places));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return vehicles;

	}

	public int count() throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(COUNT_VEHICLES_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("count");
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
		return 0;
	}

	public void delete(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public void update(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VEHICLE_QUERY);
			preparedStatement.setString(1, vehicle.getConstructeur());
			preparedStatement.setInt(2, vehicle.getNb_places());
			preparedStatement.setLong(3, vehicle.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public int countVehicleByClientId(long clientId) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(COUNT_VEHICLES_BY_CLIENT_QUERY);
			preparedStatement.setLong(1, clientId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("count");
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
		return 0;
	}

}
