package net.sf.anathema.character.core.preferences;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CharacterPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, IExecutableExtension {

  public CharacterPreferencePage() {
    // nothing to do
  }

  public CharacterPreferencePage(String title) {
    super(title);
  }

  public CharacterPreferencePage(String title, ImageDescriptor image) {
    super(title, image);
  }

  @Override
  protected Control createContents(Composite parent) {
    initializeDialogUnits(parent);
    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
    layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
    composite.setLayout(layout);
    new Label(composite, SWT.NONE).setText("Hasänpreference");
    return composite;
  }

  @Override
  public void init(IWorkbench workbench) {
    // TODO Auto-generated method stub
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // TODO Auto-generated method stub
  }
}