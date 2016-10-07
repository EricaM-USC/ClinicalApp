import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.opencsv.CSVWriter;

import Database.DatabaseConnection;
import Database.NotesEntry;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SearchInputPage {

	protected Shell shlClinicalRecordsSearch;
	private Text AccessNumber_box;
	private Text History_Box;
	private Text Examination_box;
	private Text Assessment_Box;
	private Text PlanBox;
	private Text RecordDate_box;
	private Button btnPrintToPdf;
	private Label lblUseAnyOf;
	private Text VetBox;
	private Label lblVet;
	private Label lblNewToSearch;
	private Button button;
	private DatabaseConnection dc;
	private Text animalName_Box;
	private Label lblAnimalName;
	private Text species_Box;
	private Label lblSpecies;
	private Label lblStatus;
	private Text status_Box;
	private Button btnViewResults;
	
	/**
	 * Launch the application.
	 * @param args
	 *
	public static void main(String[] args) {
		try {
			SearchInputPage window = new SearchInputPage();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open(DatabaseConnection dc) {
		this.dc = dc;
		Display display = Display.getDefault();
		createContents();
		shlClinicalRecordsSearch.open();
		shlClinicalRecordsSearch.layout();
		while (!shlClinicalRecordsSearch.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlClinicalRecordsSearch = new Shell();
		shlClinicalRecordsSearch.setSize(504, 479);
		shlClinicalRecordsSearch.setText("Clinical Records Search");
		
		AccessNumber_box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		AccessNumber_box.setBounds(140, 122, 136, 19);
		
		History_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		History_Box.setBounds(140, 247, 136, 19);
		
		Examination_box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		Examination_box.setBounds(140, 272, 136, 19);
		
		Assessment_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		Assessment_Box.setBounds(140, 297, 136, 19);
		
		PlanBox = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		PlanBox.setBounds(140, 322, 136, 19);
		
		Label lblAccessionNumber = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblAccessionNumber.setBounds(20, 127, 113, 14);
		lblAccessionNumber.setText("Accession Number");
		
		Label lblHistory = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblHistory.setBounds(86, 252, 47, 14);
		lblHistory.setText("History");
		
		Label lblExamination = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblExamination.setBounds(56, 277, 77, 14);
		lblExamination.setText("Examination");
		
		Label lblAssessment = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblAssessment.setBounds(56, 302, 77, 14);
		lblAssessment.setText("Assessment");
		
		Label lblPlan = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblPlan.setBounds(97, 327, 36, 14);
		lblPlan.setText("Plan");
		
		RecordDate_box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		RecordDate_box.setBounds(140, 347, 136, 19);
		
		Label lblRecordDate = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblRecordDate.setBounds(56, 352, 77, 14);
		lblRecordDate.setText("Record Date");
		
		Button btnExportTocsv = new Button(shlClinicalRecordsSearch, SWT.NONE);
		btnExportTocsv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String accessNum = AccessNumber_box.getText().trim();
				String name= animalName_Box.getText().trim();
				String species = species_Box.getText().trim();
				String status = status_Box.getText().trim();
				String hist = History_Box.getText().trim();
				String exam = Examination_box.getText().trim();
				String assess = Assessment_Box.getText().trim();
				String plan = PlanBox.getText().trim();
				String date = RecordDate_box.getText().trim();
				String vet = VetBox.getText().trim();
				
				// Save dialog
				FileDialog fd = new FileDialog(shlClinicalRecordsSearch, SWT.SAVE);
		        fd.setText("Save");
		        fd.setFilterPath("C:/");
		        String[] filterExt = { "*.csv"};
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        System.out.println(selected);
				try {
					CSVWriter csvWriter = new CSVWriter(new FileWriter(selected), ',');
					// Run the query in the connection class and get all the results.
					ResultSet myResultSet = dc.search(accessNum, name, species, vet, date, 
							hist, exam, assess, plan, status);
					csvWriter.writeAll(myResultSet, true);
					csvWriter.close();
					
				} catch (IOException ioe){
					ioe.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// Success box
				MessageBox messageBox = new MessageBox(shlClinicalRecordsSearch, SWT.ICON_INFORMATION | SWT.OK);
		        
		        messageBox.setText("Success");
		        messageBox.setMessage("Success! Excel saved at: " + selected);
		        messageBox.open();
		        shlClinicalRecordsSearch.close();
			}
		});
		btnExportTocsv.setBounds(150, 419, 124, 28);
		btnExportTocsv.setText("Export to Excel");
		
		btnPrintToPdf = new Button(shlClinicalRecordsSearch, SWT.NONE);
		btnPrintToPdf.setBounds(280, 419, 118, 28);
		btnPrintToPdf.setText("Print to PDF");
		
		lblUseAnyOf = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblUseAnyOf.setFont(SWTResourceManager.getFont(".SF NS Text", 14, SWT.BOLD));
		lblUseAnyOf.setBounds(52, 17, 413, 28);
		lblUseAnyOf.setText("Use any boxes to enter key words you wish to search for. \n\n\n\n");
		
		VetBox = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		VetBox.setBounds(140, 222, 136, 19);
		
		lblVet = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblVet.setBounds(102, 227, 31, 14);
		lblVet.setText("Vet");
		
		lblNewToSearch = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblNewToSearch.setBounds(27, 51, 438, 50);
		lblNewToSearch.setText("- To search for all the animal records from Rosie put 'RJB' in the Vet Field. \n- To search for all the animals treated by Rosie on 7th October, 2016 \n    put 'RJB' in Vet and '2016-10-17' in Record Date");
		
		button = new Button(shlClinicalRecordsSearch, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlClinicalRecordsSearch.close();
			}
		});
		button.setText("Cancel");
		button.setBounds(404, 419, 94, 28);
		
		animalName_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		animalName_Box.setBounds(140, 147, 136, 19);
		
		lblAnimalName = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblAnimalName.setBounds(51, 152, 82, 14);
		lblAnimalName.setText("Animal Name");
		
		species_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		species_Box.setBounds(140, 172, 136, 19);
		
		lblSpecies = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblSpecies.setBounds(77, 177, 56, 14);
		lblSpecies.setText("Species");
		
		lblStatus = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblStatus.setBounds(86, 202, 47, 14);
		lblStatus.setText("Status");
		
		status_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		status_Box.setBounds(139, 197, 137, 19);
		
		btnViewResults = new Button(shlClinicalRecordsSearch, SWT.NONE);
		btnViewResults.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Shaun's part 
				String accessNum = AccessNumber_box.getText().trim();
				String name= animalName_Box.getText().trim();
				String species = species_Box.getText().trim();
				String status = status_Box.getText().trim();
				String hist = History_Box.getText().trim();
				String exam = Examination_box.getText().trim();
				String assess = Assessment_Box.getText().trim();
				String plan = PlanBox.getText().trim();
				String date = RecordDate_box.getText().trim();
				String vet = VetBox.getText().trim();
				
				ResultSet searchResults = dc.search(accessNum, name, species, vet, date, 
							hist, exam, assess, plan, status);
				ArrayList<NotesEntry> notesEntriesToView = NotesEntry.buildFromRS(searchResults);
				
				// Got you started 
				// - need to open a new window with whatever control you want (a rich text box gives you formatting btw)
				// with the data that is in that result set :-)
			}
		});
		btnViewResults.setBounds(20, 419, 124, 28);
		btnViewResults.setText("View Results");
		shlClinicalRecordsSearch.setTabList(new Control[]{AccessNumber_box, animalName_Box, species_Box, status_Box, VetBox, History_Box, Examination_box, Assessment_Box, PlanBox, RecordDate_box, btnExportTocsv, btnPrintToPdf});
	
	}
}
