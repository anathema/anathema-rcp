/**
 * 
 */
package net.sf.anathema.campaign.note;

import java.io.File;

import net.sf.anathema.basics.swt.file.FileChoosing;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class NoteFileSelectionWizardPage extends WizardPage {
  private File file;

  public NoteFileSelectionWizardPage() {
    super("Anathema Classic Note", "Import a Note from Anathema Classic", null);
    setDescription("Please select a Note to import.");
  }

  @Override
  public boolean isPageComplete() {
    return file != null && file.exists();
  }

  @Override
  public void createControl(final Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(3, false));
    Label label = new Label(composite, SWT.None);
    label.setText("File name:");
    final Text text = new Text(composite, SWT.SINGLE | SWT.BORDER);
    text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    text.addModifyListener(new ModifyListener() {
      @Override
      public void modifyText(ModifyEvent e) {
        file = new File(text.getText());
        if (file.isDirectory()) {
          setMessage("You have specified a folder.", IMessageProvider.ERROR);
        }
        else if (file.exists()) {
          setMessage("Click 'Finish' to import the selected file.");
        }
        else {
          setMessage("The specified file does not exist.", IMessageProvider.ERROR);
        }
        setPageComplete(false);
      }
    });
    Button button = new Button(composite, SWT.PUSH);
    button.setText("Browse...");
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseUp(MouseEvent e) {
        File selectedFile = FileChoosing.openNoteFile(null, parent.getShell());
        if (selectedFile != null) {
          text.setText(selectedFile.getAbsolutePath());
        }
      }
    });
    // TODO: Open when finished
    setControl(composite);
  }
}