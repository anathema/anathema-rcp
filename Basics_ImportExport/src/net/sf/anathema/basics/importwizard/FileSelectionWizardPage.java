package net.sf.anathema.basics.importwizard;

import java.io.File;

import net.disy.commons.core.model.BooleanModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.importwizard.control.FileDisplayNameUpdater;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class FileSelectionWizardPage extends WizardPage {
  private final IFileSelectionModel model;
  private final BooleanModel openModel;
  private final IFileSelectionPageMessages messages;
  private final IFileDialog dialog;

  public FileSelectionWizardPage(
      final IFileSelectionModel model,
      BooleanModel openModel,
      IFileSelectionPageMessages messages,
      IFileDialog dialog) {
    super(messages.getPageName(), messages.getPageTitle(), null);
    this.openModel = openModel;
    this.model = model;
    this.messages = messages;
    this.dialog = dialog;
    setDescription(messages.getDescription());
    model.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        final IMessageProvider message = model.getMessage();
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
    createOpenCheckBox(composite);
    setControl(composite);
  }

  private void createOpenCheckBox(Composite composite) {
    final Button checkBox = new Button(composite, SWT.CHECK);
    GridData gridData = new GridData();
    gridData.horizontalSpan = 3;
    checkBox.setLayoutData(gridData);
    checkBox.setText(messages.getOpenLabel());
    checkBox.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        updateModel();
      }

      @Override
      public void widgetSelected(SelectionEvent e) {
        updateModel();
      }

      private void updateModel() {
        openModel.setValue(checkBox.getSelection());
      }
    });
  }

  private void createLabel(final Composite composite) {
    Label label = new Label(composite, SWT.None);
    label.setText(Messages.FileSelectionWizardPage_FileNameLabel);
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
    model.addChangeListener(new FileDisplayNameUpdater(text, model));
  }

  private void createBrowseButton(final Composite composite) {
    Button button = new Button(composite, SWT.PUSH);
    button.setText(Messages.FileSelectionWizardPage_BrowseButtonLabel);
    button.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        getFile();
      }

      @Override
      public void widgetSelected(SelectionEvent e) {
        getFile();
      }

      private void getFile() {
        File selectedFile = dialog.open(composite.getShell());
        model.setFile(selectedFile);
      }
    });
  }
}