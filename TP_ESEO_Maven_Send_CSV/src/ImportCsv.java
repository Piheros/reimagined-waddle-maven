import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.opencsv.CSVReader;

public class ImportCsv {
	
	public static void readCsv(){

		try (CSVReader reader = new CSVReader(new FileReader("ville_france.csv"), ','); 
                Connection connection = JDBCConfigurationSol2.getConnection();) {
			
			//String insertQuery = "Insert into txn_tbl (txn_id,txn_amount, card_number, terminal_id) values (null,?,?,?)";
			String insertQuery = "Insert into send_ville_france (Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) values (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(insertQuery);
			
			String[] rowData = null;
			int i = 0;
			
			while((rowData = reader.readNext()) != null) {
				for (String data : rowData) {
					pstmt.setString((i % 7) + 1, data);

					if (++i % 7 == 0)
						pstmt.addBatch();// add batch

					if (i % 30 == 0)// insert when the batch size is 10
						pstmt.executeBatch();
				}
			}
			System.out.println("Data Successfully Uploaded");
	}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void readCsvUsingLoad() {
		try (Connection connection = JDBCConfigurationSol2.getConnection())	{

			//String loadQuery = "LOAD DATA LOCAL INFILE '" + "C:\\upload.csv" + "' INTO TABLE txn_tbl FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (txn_amount, card_number, terminal_id) ";
			String loadQuery = "LOAD DATA LOCAL INFILE '" + "C:\\ville_france.csv" + "' INTO TABLE send_ville_france FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) ";	
			
			System.out.println(loadQuery);
			Statement stmt = connection.createStatement();
			stmt.execute(loadQuery);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
