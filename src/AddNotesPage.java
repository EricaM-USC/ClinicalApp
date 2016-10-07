import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Database.*;

import java.util.Calendar;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Combo;

public class AddNotesPage {

	protected Shell shlAddClinicalNotes;
	private Text accession_text_box;
	private Text name_text_box;
	private Text date_box;
	private Text history_text_box;
	private Text examination_box;
	private Text assessment_box;
	private Text plan_text_box;
	private Text bodyweight_box;
	private Text respiratory_box;
	private DatabaseConnection dc;
	private Text vetname_Box;
	private Combo vet_combo;
	private Combo statusCombo;
	private Text Species_box;

	/**
	 * Launch the application.
	 * @param args
	 *
	public static void main(String[] args) {
		try {
			
			AddNotesPage window = new AddNotesPage();
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
		shlAddClinicalNotes.open();
		shlAddClinicalNotes.layout();
		while (!shlAddClinicalNotes.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAddClinicalNotes = new Shell();
		shlAddClinicalNotes.setSize(478, 497);
		shlAddClinicalNotes.setText("Add Clinical Notes");
		
		accession_text_box = new Text(shlAddClinicalNotes, SWT.BORDER);
		accession_text_box.setBounds(125, 10, 84, 19);
		
		Label lblAccessionNumber = new Label(shlAddClinicalNotes, SWT.NONE);
		lblAccessionNumber.setBounds(10, 13, 109, 28);
		lblAccessionNumber.setText("Accession Number");
		
		Button btnNew = new Button(shlAddClinicalNotes, SWT.NONE);
		btnNew.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = name_text_box.getText();
				String strDate = date_box.getText();
				String accession = accession_text_box.getText();
				String history = history_text_box.getText();
				String examination = examination_box.getText();
				String assessment = examination_box.getText();
				String plan = examination_box.getText();
				String vet = vet_combo.getText();
				String species = Species_box.getText();
				String status = statusCombo.getText();
				double bodyWeight = 0.0;
				int respiratory = 0;
				
				// get the time to make sure the entries have a unique key
				//get current date time with Calendar()
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				String time = dateFormat.format(Calendar.getInstance().getTime());
				try {
					bodyWeight = Double.parseDouble(bodyweight_box.getText());		
					respiratory = Integer.parseInt(respiratory_box.getText());
					 
				} catch (NumberFormatException e1)  {
					e1.printStackTrace();
				}
				NotesEntry ne = new NotesEntry(accession, name, species, strDate, 
						time, vet, history, examination, assessment, plan, "initial", 
						bodyWeight, respiratory, status);
//				history_text_box.setText("Test");
				dc.insertNotesEntry(ne);
				
			}
		});
		btnNew.setBounds(250, 447, 118, 28);
		btnNew.setText("Create Record");
		
		name_text_box = new Text(shlAddClinicalNotes, SWT.BORDER);
		name_text_box.setBounds(125, 35, 84, 19);
		
		Label lblNewLabel = new Label(shlAddClinicalNotes, SWT.NONE);
		lblNewLabel.setBounds(10, 38, 59, 14);
		lblNewLabel.setText("Name");
		
		date_box = new Text(shlAddClinicalNotes, SWT.BORDER);
		date_box.setBounds(296, 35, 99, 19);
		//set date
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todaysDate = dateFormat.format(Calendar.getInstance().getTime());
		date_box.setText(todaysDate);
		
		Label lblNewLabel_1 = new Label(shlAddClinicalNotes, SWT.NONE);
		lblNewLabel_1.setBounds(231, 38, 59, 14);
		lblNewLabel_1.setText("Date");
		
		Label lblNewLabel_2 = new Label(shlAddClinicalNotes, SWT.NONE);
		lblNewLabel_2.setBounds(10, 81, 59, 14);
		lblNewLabel_2.setText("History");
		
		history_text_box = new Text(shlAddClinicalNotes, SWT.BORDER);
		history_text_box.setBounds(10, 101, 429, 46);
		
		Label lblExamination = new Label(shlAddClinicalNotes, SWT.NONE);
		lblExamination.setText("Examination");
		lblExamination.setBounds(10, 153, 75, 14);
		
		examination_box = new Text(shlAddClinicalNotes, SWT.BORDER);
		examination_box.setBounds(10, 173, 429, 52);
		
		Label lblAssessment = new Label(shlAddClinicalNotes, SWT.NONE);
		lblAssessment.setText("Assessment");
		lblAssessment.setBounds(10, 231, 87, 14);
		
		assessment_box = new Text(shlAddClinicalNotes, SWT.BORDER);
		assessment_box.setBounds(10, 251, 429, 59);
		
		Button btnCancel = new Button(shlAddClinicalNotes, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAddClinicalNotes.close();
			}
		});
		btnCancel.setBounds(374, 447, 94, 28);
		btnCancel.setText("Cancel");
		
		Label lblPlan = new Label(shlAddClinicalNotes, SWT.NONE);
		lblPlan.setText("Plan");
		lblPlan.setBounds(10, 316, 87, 14);
		
		plan_text_box = new Text(shlAddClinicalNotes, SWT.BORDER);
		plan_text_box.setBounds(10, 336, 429, 59);
		
		Label lblNewLabel_3 = new Label(shlAddClinicalNotes, SWT.NONE);
		lblNewLabel_3.setBounds(10, 411, 75, 14);
		lblNewLabel_3.setText("Body Weight:");
		
		bodyweight_box = new Text(shlAddClinicalNotes, SWT.BORDER);
		bodyweight_box.setBounds(91, 406, 84, 19);
		
		Label lblNewLabel_4 = new Label(shlAddClinicalNotes, SWT.NONE);
		lblNewLabel_4.setBounds(250, 409, 109, 14);
		lblNewLabel_4.setText("Respiratory Rate:");
		
		respiratory_box = new Text(shlAddClinicalNotes, SWT.BORDER);
		respiratory_box.setBounds(375, 406, 64, 19);
		
		Label lblVet = new Label(shlAddClinicalNotes, SWT.NONE);
		lblVet.setBounds(231, 10, 59, 14);
		lblVet.setText("Vet");
		
		//vetname_Box = new Text(shlAddClinicalNotes, SWT.BORDER);
		//vetname_Box.setBounds(296, 10, 64, 19);
		
		vet_combo = new Combo(shlAddClinicalNotes, SWT.NONE);
		vet_combo.setBounds(296, 10, 130, 31);
		vet_combo.setItems(dc.getVets());
		
		Label lblSpecies = new Label(shlAddClinicalNotes, SWT.NONE);
		lblSpecies.setBounds(10, 61, 59, 14);
		lblSpecies.setText("Species");
		
		Species_box = new Text(shlAddClinicalNotes, SWT.BORDER);
		Species_box.setBounds(125, 60, 84, 19);
		
		statusCombo = new Combo(shlAddClinicalNotes, SWT.NONE);
		statusCombo.setBounds(296, 60, 130, 22);
		statusCombo.setItems(dc.getStatus());
		
		Label lblNewLabel_5 = new Label(shlAddClinicalNotes, SWT.NONE);
		lblNewLabel_5.setBounds(231, 61, 59, 14);
		lblNewLabel_5.setText("Status");
		shlAddClinicalNotes.setTabList(new Control[]{accession_text_box, vet_combo, name_text_box, date_box, history_text_box, examination_box, assessment_box, plan_text_box, bodyweight_box, respiratory_box, btnNew, btnCancel});

	}
}
