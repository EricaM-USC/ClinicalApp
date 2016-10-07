package Database;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

	private Connection conn;
	
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicalSystem", "root",
					"1ricSmiles.");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public String[] getVets(){
		ArrayList<String> vets = new ArrayList<String>();
	
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT initials FROM Vet_Names");
			while(rs.next()){
				vets.add(rs.getString("initials"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		String [] s = {};
		return vets.toArray(s);
	}
	
	public String[] getStatus(){
		String [] s = {"AZWH","Carer", "Dead", "Released"};
		return s;
	}
	
	public boolean insertNotesEntry(NotesEntry ne) {
		boolean success = false;
		try {
		    String query = "INSERT INTO vetnotes values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    PreparedStatement pstmt = conn.prepareStatement(query);
		
		    pstmt.setString(1, ne.getAccessionNumber());
		    pstmt.setString(2, ne.getAnimalName());
		    pstmt.setString(3, ne.getDate());
		    pstmt.setString(4, ne.getTime());
		    pstmt.setString(5, ne.getVet());
		    pstmt.setString(6, ne.getHistory());
		    pstmt.setString(7, ne.getExamination());
		    pstmt.setString(8, ne.getAssessment());
		    pstmt.setString(9, ne.getPlan());
		    pstmt.setString(10, ne.getType());
		    pstmt.setDouble(12, ne.getBodyWeight());
		    pstmt.setInt(11, ne.getResp());
		      
		    success = pstmt.execute();
		      
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return success;
		
	}
	
	public ArrayList<NotesEntry> executeSQL(String sqlString){
		ArrayList<NotesEntry> ne= null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			ne = NotesEntry.buildFromRS(rs);
		} catch (Exception e) {
			System.out.println(e);
		}
		return ne;
	}
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
