package com;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.JDBCConfigurationSol2;

public class App {
    public static void main( String[] args ) {
        try { 
			Connection conn = JDBCConfigurationSol2.getConnection();
			Statement stmt = conn.createStatement();
			String strSelect = "select Nom_commune, Code_postal from ville_france WHERE Code_postal= '01110'";
			System.out.println("La requete SQL est: " + strSelect + "\n");
			
			// Echo For debugging System.out.println();
			ResultSet results = stmt.executeQuery(strSelect);
			
			System.out.println("La selection pour le code postal est :");
			int rowCount = 0; while (results.next()) {
				// Move the cursor to the next row, return false if no more row 
				String Commune = results.getString("Nom_commune"); 
				String codePostal = results.getString("Code_postal"); 
				System.out.println("Commune: " + Commune + " et code postal: " + codePostal); ++rowCount;
				} 
			
			System.out.println("\nNombre total des communes : " + rowCount);
			
			//Find ville
			VilleFranceDAOClient.findVille();
						
			} catch (SQLException ex) { 
				ex.printStackTrace();
			} 
        
        	//Version
        	Version.getVersion();
    }
}
