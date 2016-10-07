import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class FrontPage {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shlClinicalNotesSystem = new Shell();
		shlClinicalNotesSystem.setSize(450, 300);
		shlClinicalNotesSystem.setText("Clinical Notes System");
		
		Button NewNotesButton = new Button(shlClinicalNotesSystem, SWT.NONE);
		NewNotesButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					
					AddNotesPage window = new AddNotesPage();
					window.open();
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		NewNotesButton.setBounds(51, 154, 150, 35);
		NewNotesButton.setText("Add New Entry");
		
		Button btnNewButton = new Button(shlClinicalNotesSystem, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					SearchInputPage window = new SearchInputPage();
					window.open();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(247, 154, 150, 35);
		btnNewButton.setText("Search");
		
		Label lblAzwhuscClinicalVet = new Label(shlClinicalNotesSystem, SWT.NONE);
		lblAzwhuscClinicalVet.setFont(SWTResourceManager.getFont("Apple SD Gothic Neo", 24, SWT.NORMAL));
		lblAzwhuscClinicalVet.setAlignment(SWT.CENTER);
		lblAzwhuscClinicalVet.setBounds(29, 59, 396, 46);
		lblAzwhuscClinicalVet.setText("AZWH-USC Clinical Vet Notes System");
		
		Button btnClose = new Button(shlClinicalNotesSystem, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setText("Close");
		btnClose.setBounds(149, 220, 150, 35);

		shlClinicalNotesSystem.open();
		shlClinicalNotesSystem.layout();
		while (!shlClinicalNotesSystem.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
