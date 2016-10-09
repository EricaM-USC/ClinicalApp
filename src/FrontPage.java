import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import Database.DatabaseConnection;
import org.eclipse.swt.widgets.Canvas;

public class FrontPage {

	private static DatabaseConnection dc;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		dc = new DatabaseConnection();
		Display display = Display.getDefault();
		Shell shlClinicalNotesSystem = new Shell();
		shlClinicalNotesSystem.setBackground(SWTResourceManager.getColor(0, 128, 128));
		shlClinicalNotesSystem.setSize(478, 442);
		shlClinicalNotesSystem.setText("Clinical Notes System");
		DatabaseConnection dc = new DatabaseConnection();
		Button NewNotesButton = new Button(shlClinicalNotesSystem, SWT.NONE);
		NewNotesButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					
					AddNotesPage window = new AddNotesPage();
					window.open(dc);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		NewNotesButton.setBounds(69, 309, 150, 35);
		NewNotesButton.setText("Add New Entry");
		
		Button btnNewButton = new Button(shlClinicalNotesSystem, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					SearchInputPage window = new SearchInputPage();
					window.open(dc);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(265, 309, 150, 35);
		btnNewButton.setText("Search");
		
		Label lblAzwhuscClinicalVet = new Label(shlClinicalNotesSystem, SWT.NONE);
		lblAzwhuscClinicalVet.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblAzwhuscClinicalVet.setFont(SWTResourceManager.getFont("Apple SD Gothic Neo", 24, SWT.NORMAL));
		lblAzwhuscClinicalVet.setAlignment(SWT.CENTER);
		lblAzwhuscClinicalVet.setBounds(43, 245, 396, 46);
		lblAzwhuscClinicalVet.setText("Clinical Vet Notes System");
		
		Button btnClose = new Button(shlClinicalNotesSystem, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setText("Close");
		btnClose.setBounds(167, 375, 150, 35);
		
		Label lblNewLabel = new Label(shlClinicalNotesSystem, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage("JointUSC-AZWH.png"));
		lblNewLabel.setBounds(23, 29, 430, 210);

		shlClinicalNotesSystem.open();
		shlClinicalNotesSystem.layout();
		while (!shlClinicalNotesSystem.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
