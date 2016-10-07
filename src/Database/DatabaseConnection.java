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
			rs = stmt.executeQuery("SELECT initials FROM vetNames");
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
		    String query = "INSERT INTO vetNotes values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    PreparedStatement pstmt = conn.prepareStatement(query);
		
		    pstmt.setString(1, ne.getAccessionNumber());
		    pstmt.setString(2, ne.getAnimalName());
		    pstmt.setString(3, ne.getSpecies());
		    pstmt.setString(4, ne.getDate());
		    pstmt.setString(5, ne.getTime());
		    pstmt.setString(6, ne.getVet());
		    pstmt.setString(7, ne.getHistory());
		    pstmt.setString(8, ne.getExamination());
		    pstmt.setString(9, ne.getAssessment());
		    pstmt.setString(10, ne.getPlan());
		    pstmt.setString(11, ne.getType());
		    pstmt.setDouble(12, ne.getBodyWeight());
		    pstmt.setInt(13, ne.getResp());
		    pstmt.setString(14, ne.getAnimalStatus());  
		    success = pstmt.execute();
		      
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return success;
		
	}
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//public ArrayList<NotesEntry> search(String accessNum, String name, String species,
		//	String vet, String date, String hist, String exam, 
			//String assess, String plan, String status){
		public ResultSet search(String accessNum, String name, String species,
				String vet, String date, String hist, String exam, 
				String assess, String plan, String status){
		
		//ArrayList<NotesEntry> searchResults = new ArrayList<NotesEntry>();
		ResultSet searchResults = null;
		// ericas part that calls the search
		String query = "SELECT * FROM vetNotes";
		String whereString = " WHERE ";
		String joinString = "";
		ArrayList<String> queryParameters = new ArrayList<String>();
		/* after the first condition is met where string is empyt and AND will be prepended to the condition*/
		if (!accessNum.isEmpty()){
			query += whereString + joinString + " (accessionNumber LIKE ?)";
			queryParameters.add(accessNum);
			whereString = "";
			joinString = " AND ";
		}
		if (!name.isEmpty()){
			query += whereString + joinString + " (animalName LIKE ?)";
			queryParameters.add(name);
			whereString = "";
			joinString = " AND ";
		}
		if (!species.isEmpty()){
			query += whereString + joinString + " (species LIKE ?)";
			queryParameters.add(species);
			whereString = "";
			joinString = " AND ";
		}
		if (!status.isEmpty()){
			query += whereString + joinString + " (status LIKE ?)";
			queryParameters.add(status);
			whereString = "";
			joinString = " AND ";
		}
		if (!vet.isEmpty()){
			query += whereString + joinString + " (vet LIKE ?)";
			queryParameters.add(vet);
			whereString = "";
			joinString = " AND ";
		}
		
		if (!date.isEmpty()){
			query += whereString + joinString + " (noteDate LIKE ?)";
			queryParameters.add(date);
			whereString = "";
			joinString = " AND ";
		}
		if (!hist.isEmpty()){
			query += whereString + joinString + " (history LIKE ?)";
			queryParameters.add(hist);
			whereString = "";
			joinString = " AND ";
		}
		if (!exam.isEmpty()){
			query += whereString + joinString + " (examination LIKE ?)";
			queryParameters.add(exam);
			whereString = "";
			joinString = " AND ";
		}
		if (!assess.isEmpty()){
			query += whereString + joinString + " (assessment LIKE ?)";
			queryParameters.add(assess);
			whereString = "";
			joinString = " AND ";
		}
		if (!plan.isEmpty()){
			query += whereString + joinString + " (plan LIKE ?)";
			queryParameters.add(plan);
			whereString = "";
			joinString = " AND ";
		}
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			int counter = 1;
			for (String s: queryParameters){
				pstmt.setString(counter,"%"+ s + "%");
				counter++;
			}
			//ResultSet rs = pstmt.executeQuery();
			//searchResults = NotesEntry.buildFromRS(rs);
			searchResults = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchResults;
	}
}
