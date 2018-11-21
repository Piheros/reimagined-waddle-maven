import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try { 
			Connection conn = JDBCConfigurationSol2.getConnection();
			Statement stmt = conn.createStatement();
			String strSelect = "select Nom_commune, Code_postal from ville_france WHERE Code_postal= '01090'";
			System.out.println("The SQL query is: " + strSelect);
			// Echo For debugging 
			System.out.println();
			ResultSet results = stmt.executeQuery(strSelect);
			System.out.println("The records selected are:");
			int rowCount = 0; while (results.next()) {
				// Move the cursor to the next row, return false if no more row 
				String Commune = results.getString("Nom_commune"); 
				String codePostal = results.getString("Code_postal"); 
				System.out.println(Commune + ", " + codePostal); ++rowCount;
				} 
			System.out.println("Total number of records = " + rowCount);
			} catch (SQLException ex) { 
				ex.printStackTrace();
			} 
    }
}
