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
	private Label label;
	private Label label_1;
	
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
		shlClinicalRecordsSearch.setBackground(SWTResourceManager.getColor(0, 128, 128));
		shlClinicalRecordsSearch.setSize(504, 559);
		shlClinicalRecordsSearch.setText("Clinical Records Search");
		
		AccessNumber_box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		AccessNumber_box.setBounds(237, 207, 136, 19);
		
		History_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		History_Box.setBounds(237, 332, 136, 19);
		
		Examination_box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		Examination_box.setBounds(237, 357, 136, 19);
		
		Assessment_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		Assessment_Box.setBounds(237, 382, 136, 19);
		
		PlanBox = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		PlanBox.setBounds(237, 407, 136, 19);
		
		Label lblAccessionNumber = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblAccessionNumber.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		lblAccessionNumber.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblAccessionNumber.setBounds(117, 212, 113, 14);
		lblAccessionNumber.setText("Accession Number");
		
		Label lblHistory = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblHistory.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		lblHistory.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblHistory.setBounds(183, 337, 47, 14);
		lblHistory.setText("History");
		
		Label lblExamination = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblExamination.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		lblExamination.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblExamination.setBounds(153, 362, 77, 14);
		lblExamination.setText("Examination");
		
		Label lblAssessment = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblAssessment.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		lblAssessment.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblAssessment.setBounds(153, 387, 77, 14);
		lblAssessment.setText("Assessment");
		
		Label lblPlan = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblPlan.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		lblPlan.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblPlan.setBounds(194, 412, 36, 14);
		lblPlan.setText("Plan");
		
		RecordDate_box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		RecordDate_box.setBounds(237, 432, 136, 19);
		
		Label lblRecordDate = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblRecordDate.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		lblRecordDate.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblRecordDate.setBounds(153, 437, 77, 14);
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
		btnExportTocsv.setBounds(140, 499, 124, 28);
		btnExportTocsv.setText("Export to Excel");
		
		btnPrintToPdf = new Button(shlClinicalRecordsSearch, SWT.NONE);
		btnPrintToPdf.setBounds(270, 499, 118, 28);
		btnPrintToPdf.setText("Print to PDF");
		
		lblUseAnyOf = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblUseAnyOf.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblUseAnyOf.setFont(SWTResourceManager.getFont(".SF NS Text", 18, SWT.BOLD));
		lblUseAnyOf.setBounds(42, 97, 413, 28);
		lblUseAnyOf.setText("Use any boxes to enter key words you wish to search for. \n\n\n\n");
		
		VetBox = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		VetBox.setBounds(237, 307, 136, 19);
		
		lblVet = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblVet.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		lblVet.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblVet.setBounds(199, 312, 31, 14);
		lblVet.setText("Vet");
		
		lblNewToSearch = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblNewToSearch.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.ITALIC));
		lblNewToSearch.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblNewToSearch.setBounds(17, 131, 438, 50);
		lblNewToSearch.setText("- To search for all the animal records from Rosie put 'RJB' in the Vet Field. \n- To search for all the animals treated by Rosie on 7th October, 2016 \n    put 'RJB' in Vet and '2016-10-17' in Record Date");
		
		button = new Button(shlClinicalRecordsSearch, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlClinicalRecordsSearch.close();
			}
		});
		button.setText("Cancel");
		button.setBounds(394, 499, 94, 28);
		
		animalName_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		animalName_Box.setBounds(237, 232, 136, 19);
		
		lblAnimalName = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblAnimalName.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		lblAnimalName.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblAnimalName.setBounds(148, 237, 82, 14);
		lblAnimalName.setText("Animal Name");
		
		species_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		species_Box.setBounds(237, 257, 136, 19);
		
		lblSpecies = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblSpecies.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		lblSpecies.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblSpecies.setBounds(174, 262, 56, 14);
		lblSpecies.setText("Species");
		
		lblStatus = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblStatus.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		lblStatus.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblStatus.setBounds(183, 287, 47, 14);
		lblStatus.setText("Status");
		
		status_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		status_Box.setBounds(236, 282, 137, 19);
		
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
		btnViewResults.setBounds(10, 499, 124, 28);
		btnViewResults.setText("View Results");
		
		label = new Label(shlClinicalRecordsSearch, SWT.NONE);
		label.setText("Clinical Vet Notes System");
		label.setForeground(SWTResourceManager.getColor(255, 255, 255));
		label.setFont(SWTResourceManager.getFont(".SF NS Text", 14, SWT.BOLD));
		label.setBounds(255, 43, 189, 28);
		
		label_1 = new Label(shlClinicalRecordsSearch, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage("JointUSC-AZWH-long-small.png"));
		label_1.setBounds(50, 29, 213, 54);
		shlClinicalRecordsSearch.setTabList(new Control[]{AccessNumber_box, animalName_Box, species_Box, status_Box, VetBox, History_Box, Examination_box, Assessment_Box, PlanBox, RecordDate_box, btnExportTocsv, btnPrintToPdf});
	
	}
}
