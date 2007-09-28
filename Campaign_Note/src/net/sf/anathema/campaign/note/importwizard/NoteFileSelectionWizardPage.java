package net.sf.anathema.campaign.note.importwizard;

import java.io.File;

import net.disy.commons.core.model.listener.IChangeListener;
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
  private final IFileSelectionModel model;

  public NoteFileSelectionWizardPage(final IFileSelectionModel model) {
    super("Anathema Classic Note", "Import a Note from Anathema Classic", null);
    setDescription("Please select a Note to import.");
    this.model = model;
    final IMessageProvider message = model.getMessage();
    model.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        setMessage(message.getMessage(), message.getMessageType());
        getContainer().updateButtons();
      }
    });
  }

  @Override
  public boolean isPageComplete() {
    return model.isComplete();
  }

  @Override
  public void createControl(final Composite parent) {
    final Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(3, false));
    createLabel(composite);
    createTextField(composite);
    createBrowseButton(composite);
    // TODO: Open when finished
    setControl(composite);
  }

  private void createLabel(final Composite composite) {
    Label label = new Label(composite, SWT.None);
    label.setText("File name:");
  }

  private void createTextField(final Composite composite) {
    final Text text = new Text(composite, SWT.SINGLE | SWT.BORDER);
    text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    text.addModifyListener(new ModifyListener() {
      @Override
      public void modifyText(ModifyEvent e) {
        model.setFile(text.getText());
      }
    });
    model.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        File file = model.getFile();
        if (new File(text.getText()).equals(file)) {
          return;
        }
        text.setText(file.getAbsolutePath());
      }
    });
  }

  private void createBrowseButton(final Composite composite) {
    Button button = new Button(composite, SWT.PUSH);
    button.setText("Browse...");
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseUp(MouseEvent e) {
        File selectedFile = FileChoosing.openNoteFile(null, composite.getShell());
        model.setFile(selectedFile);
      }
    });
  }
}