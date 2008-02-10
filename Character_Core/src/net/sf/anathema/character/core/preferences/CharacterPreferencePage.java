package net.sf.anathema.character.core.preferences;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CharacterPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, IExecutableExtension {

  @Override
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout());
    new Label(composite, SWT.NONE);
    return composite;
  }

  @Override
  public void init(IWorkbench workbench) {
    // nothing to do
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
  throws CoreException {
    // nothing to do
  }
}