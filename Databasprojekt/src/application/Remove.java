package application;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class Remove {
	private Connection con;
	private String connection;
	public Remove(String connection){
		this.connection=connection;
		try {
			connection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void connection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("driver true");
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
					"jdbc:mysql://"+connection+"/ae6961",
					"AE6961", "Ibanez2011");
			if (!con.isClosed()) {
				System.out.println("Successfully connected to "
						+ "MySQL server using TCP/IP...");
			}
			PreparedStatement statement = (PreparedStatement) con
					.prepareStatement("select BandNamn from band");

			ResultSet result = statement.executeQuery();

			result.next();
			System.out.println(result.getString(1));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Exception: " + e.getMessage());
		} 
			
		
	}
	
	public void removeBand(String bandname){	//Kräver delete av medlemmar och spelschema innan band kan tas bort.
		PreparedStatement statement1;
		PreparedStatement statement2;
		PreparedStatement statement3;
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("delete from speltid where Akt='"+bandname+"'");
			statement1.execute();
			
			statement2 = (PreparedStatement) con
					.prepareStatement("delete from bandmedlem where Band='"+bandname+"'");
			statement2.execute();
			
			statement3 = (PreparedStatement) con
					.prepareStatement("delete from band where BandNamn='"+bandname+"'");
			statement3.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select BandNamn from band");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1));
			
			}} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Testa igen!");
			e.printStackTrace();
		}
	}
	public void removeAct(String showID){			//Scen,Starttid,Dag
		PreparedStatement statement1;
		
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("delete from speltid where SpeltidsID='"+showID+"'");
			statement1.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select * from speltid");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1)+result4.getString(5));
			
			}JOptionPane.showMessageDialog(null, "Speltid raderad.");
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Testa igen!");
			e.printStackTrace();
		}
	}
	public void removeStage(String stageID){		
		PreparedStatement statement1;
		
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("delete from scen where ScenID='"+stageID+"'");
			statement1.execute();
			
			JOptionPane.showMessageDialog(null, "Scen raderad.");
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Är Scenen bokad? Testa igen!");
			e.printStackTrace();
		}
	}
	public void removeContact(String id){
		PreparedStatement statement1;
		
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("delete from kontaktperson where KontaktID='"+id+"'");
			statement1.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select * from kontaktperson");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1)+" "+result4.getString(2));		
			}
			JOptionPane.showMessageDialog(null, "Kontaktperson med id"+id+" är raderad.");
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Är Kontaktpersonen"
					+ " knuten till ett band? Testa igen!");
			e.printStackTrace();
		}
	}
	public void removeSecurity(String id){
		PreparedStatement statement1;
		
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("delete from sakerhetsansvarig where SakerhetsID='"+id+"'");
			statement1.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select * from sakerhetsansvarig");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1)+" "+result4.getString(2));			
			}
			JOptionPane.showMessageDialog(null, "Säkerhetsansvarig raderad.");
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Är Säkerhetsansvarig anmäld till en speltid?"
					+ " Testa igen!");
			e.printStackTrace();
		}
	}
	public void removeMember(String id){
		PreparedStatement statement1;
		
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("delete from bandmedlem where MedlemsID='"+id+"'");
			statement1.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select * from bandmedlem");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1)+" "+result4.getString(2));
			
			}} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Testa igen!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Remove q = new Remove("195.178.232.7:4040");
		try {
			q.connection();
			q.removeBand("Sillarna");
			
			q.removeContact("19901212-1212");
			q.removeSecurity("1");
			q.removeMember("90");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


