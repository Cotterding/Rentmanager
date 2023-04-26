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
		return null;
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

}
