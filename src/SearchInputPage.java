import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
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
	public void open() {
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
		shlClinicalRecordsSearch.setSize(497, 391);
		shlClinicalRecordsSearch.setText("Clinical Records Search");
		
		AccessNumber_box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		AccessNumber_box.setBounds(130, 124, 286, 19);
		
		History_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		History_Box.setBounds(130, 178, 286, 19);
		
		Examination_box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		Examination_box.setBounds(130, 206, 286, 19);
		
		Assessment_Box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		Assessment_Box.setBounds(130, 231, 286, 19);
		
		PlanBox = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		PlanBox.setBounds(130, 256, 286, 19);
		
		Label lblAccessionNumber = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblAccessionNumber.setBounds(10, 129, 114, 14);
		lblAccessionNumber.setText("Accession Number");
		
		Label lblHistory = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblHistory.setBounds(10, 181, 59, 14);
		lblHistory.setText("History");
		
		Label lblExamination = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblExamination.setBounds(10, 209, 82, 14);
		lblExamination.setText("Examination");
		
		Label lblAssessment = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblAssessment.setBounds(10, 234, 82, 14);
		lblAssessment.setText("Assessment");
		
		Label lblPlan = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblPlan.setBounds(10, 259, 59, 14);
		lblPlan.setText("Plan");
		
		RecordDate_box = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		RecordDate_box.setBounds(130, 281, 286, 19);
		
		Label lblRecordDate = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblRecordDate.setBounds(10, 279, 82, 14);
		lblRecordDate.setText("Record Date");
		
		Button btnExportTocsv = new Button(shlClinicalRecordsSearch, SWT.NONE);
		btnExportTocsv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// ericas part that calls the search
				// shaun's part load new window
				
			}
		});
		btnExportTocsv.setBounds(150, 331, 124, 28);
		btnExportTocsv.setText("Export to Excel");
		
		btnPrintToPdf = new Button(shlClinicalRecordsSearch, SWT.NONE);
		btnPrintToPdf.setBounds(280, 331, 104, 28);
		btnPrintToPdf.setText("Print to PDF");
		
		lblUseAnyOf = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblUseAnyOf.setFont(SWTResourceManager.getFont(".SF NS Text", 14, SWT.BOLD));
		lblUseAnyOf.setBounds(52, 17, 413, 28);
		lblUseAnyOf.setText("Use any boxes to enter key words you wish to search for. \n\n\n\n");
		
		VetBox = new Text(shlClinicalRecordsSearch, SWT.BORDER);
		VetBox.setBounds(130, 149, 286, 19);
		
		lblVet = new Label(shlClinicalRecordsSearch, SWT.NONE);
		lblVet.setBounds(10, 149, 59, 14);
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
		button.setBounds(393, 331, 94, 28);
		shlClinicalRecordsSearch.setTabList(new Control[]{AccessNumber_box, VetBox, History_Box, Examination_box, Assessment_Box, PlanBox, RecordDate_box, btnExportTocsv, btnPrintToPdf});

	}
}
