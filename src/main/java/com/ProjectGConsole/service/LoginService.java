
package com.ProjectGConsole.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ProjectGConsole.config.DbConfig;
import com.ProjectGConsole.model.customerModel;
import com.ProjectGConsole.util.PasswordUtil;

/**
 * Service class for handling login operations. Connects to the database,
 * verifies user credentials, and returns login status.
 */
public class LoginService {

	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection. Sets the connection error
	 * flag if the connection fails.
	 */
	public LoginService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	/**
	 * Validates the user credentials against the database records.
	 *
	 * @param customerModel the customerModel object containing user credentials
	 * @return true if the user credentials are valid, false otherwise; null if a
	 *         connection error occurs
	 */
	public Boolean loginUser(customerModel customerModel) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "SELECT customer_username, customer_password FROM customer WHERE customer_username = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, customerModel.getCustomer_username());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return validatePassword(result, customerModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return false;
	}
	
	/**
     * Fetches full user details from the database based on username.
     *
     * @param username the username to search for
     * @return CustomerModel with user details if found; otherwise null
     */
    public customerModel getUserDetails(String username) {
        if (isConnectionError) {
            System.out.println("Database connection error. Unable to fetch user details.");
            return null;
        }

        String query = "SELECT customer_name, customer_email, customer_phonenumber, customer_address, customer_gender, customer_dob, customer_username, customer_image FROM customer WHERE customer_username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                customerModel customer = new customerModel();
                customer.setCustomer_name(result.getString("customer_name"));
                customer.setCustomer_username(result.getString("customer_username"));
                customer.setCustomer_email(result.getString("customer_email"));
                customer.setCustomer_phonenumber(result.getString("customer_phonenumber"));
                customer.setCustomer_address(result.getString("customer_address"));
                customer.setCustomer_gender(result.getString("customer_gender"));
                customer.setCustomer_dob(result.getString("customer_dob"));
                customer.setCustomer_image(result.getString("customer_image"));

                return customer;
            } else {
                System.out.println("No user details found for username: " + username);
            }
        } catch (SQLException e) {
            System.out.println("Database error while fetching user details: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }


	/**
	 * Validates the password retrieved from the database.
	 *
	 * @param result       the ResultSet containing the username and password from
	 *                     the database
	 * @param studentModel the StudentModel object containing user credentials
	 * @return true if the passwords match, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	private boolean validatePassword(ResultSet result, customerModel customerModel) throws SQLException {
		String dbUsername = result.getString("customer_username");
		String dbPassword = result.getString("customer_password");

		return dbUsername.equals(customerModel.getCustomer_username())
				&& PasswordUtil.decrypt(dbPassword, dbUsername).equals(customerModel.getCustomer_password());
	}
}
