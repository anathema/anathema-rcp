package net.sf.anathema.character.trait.preference;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class TraitPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, IExecutableExtension {

  private TraitPreferences traitPreferences;

  @Override
  protected Control createContents(Composite parent) {
    traitPreferences = new TraitPreferences(getPreferenceStore());
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout());
    new Label(composite, SWT.NONE).setText(Messages.TraitPreferencePage_ExperienceTreatmentIntro);
    createButton(composite, ExperienceTraitTreatment.LeaveUnchanged, Messages.TraitPreferencePage_ExperienceTreatmentUnchanged);
    createButton(composite, ExperienceTraitTreatment.AdjustToCreation, Messages.TraitPreferencePage_ExperienceTreatmentIncrease);
    new Label(composite, SWT.NONE).setText(Messages.TraitPreferencePage_ExperienceTreatmentExplanation);
    return composite;
  }

  private Button createButton(Composite composite, final ExperienceTraitTreatment data, String text) {
    Button button = new Button(composite, SWT.RADIO);
    button.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        traitPreferences.setExperienceTreatment(data);
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
      }
    });
    button.setText(text);
    button.setSelection(traitPreferences.getExperienceTreatment() == data);
    return button;
  }

  @Override
  public void init(IWorkbench workbench) {
    // nothing to do
  }

  @Override
  public boolean performOk() {
    traitPreferences.commitChanges();
    return super.performOk();
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}