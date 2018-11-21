package com;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VilleFranceDAOClient {

	public static void findVille() {
		try {
		Connection con = JDBCConfigurationSol2.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM ville_france where Code_postal = 45100");
		while(resultSet.next()) {
			System.out.println("\nVilleFranceDAO Commune : " + resultSet.getString("Nom_commune"));
		}
		resultSet.close();
		statement.close();
	}catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
