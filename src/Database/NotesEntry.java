package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotesEntry {
	/*
	 * use clinicalSystem;
create table vetNotes(
accessionNumber varchar(50) not null,
noteDate date,
noteTime time,
vet varchar(50),
history text,
examination text,
assessment text,
plan text,
entryType enum('follow_up', 'initial'),
bodyweight decimal(3,2),
respRate int(5),
primary key (accessionNumber, NoteDate, NoteTime));
	 */
	
	private String accessionNumber;
	private String animalName;
	private String date;
	private String time;
	private String vet;
	private String history;
	private String examination;
	private String assessment;
	private String plan;
	private String type;
	private double bodyWeight;
	private int resp;
	
	public String getAccessionNumber() {
		return accessionNumber;
	}

	public String getAnimalName() {
		return animalName;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public String getVet() {
		return vet;
	}

	public String getHistory() {
		return history;
	}

	public String getExamination() {
		return examination;
	}

	public String getAssessment() {
		return assessment;
	}

	public String getPlan() {
		return plan;
	}

	public String getType() {
		return type;
	}

	public double getBodyWeight() {
		return bodyWeight;
	}

	public int getResp() {
		return resp;
	}

	public NotesEntry (String accessionNumber, String name,
		String date,
		String time,
		String vet,
		String history,
		String examination,
		String assessment,
		String plan,
		String type,
		double bodyWeight,
		int resp){
		this.accessionNumber = accessionNumber;
		this.animalName = name;
		this.date = date;
		this.time = time;
		this.vet = vet;
		this.history = history;
		this.examination = examination;
		this.assessment = assessment;
		this.plan = plan;
		this.type = type;
		this.bodyWeight = bodyWeight;
		this.resp = resp;
	}
	
	public static ArrayList<NotesEntry> buildFromRS(ResultSet rs) {
		ArrayList<NotesEntry> notesEntries = new ArrayList<NotesEntry>();
		try {
		//STEP 5: Extract data from result set
	      while(rs.next()){ 
	         //Retrieve by column name
	    	 String anum = rs.getString("accessionNumer"); 
	    	 String aname = rs.getString("animalName"); 
	    	 String d = rs.getString("date"); 
	    	 String t = rs.getString("time"); 
	    	 String v = rs.getString("vet"); 
	    	 String species = rs.getString("species"); 
	    	 String status = rs.getString("status"); 
	    	 
	    	 String h = rs.getString("history"); 
	    	 String ex = rs.getString("examination"); 
	    	 String as = rs.getString("assessment"); 
	    	 String pl = rs.getString("plan"); 
	    	 String notetype = rs.getString("type"); 
	    	 double bodyW  = rs.getDouble("bodyWeight");
	         int rr = rs.getInt("RespRate");
			
	         notesEntries.add(new NotesEntry(anum, aname, d, t, v, h, ex, as, pl, notetype, bodyW, rr));
	         
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notesEntries;
		
	}

}
