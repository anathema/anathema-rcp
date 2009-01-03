package net.sf.anathema.charms.character.preference;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charms.character.plugin.CharmCharacterPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.preference.IPreferenceStore;
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

public class CharmPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, IExecutableExtension {

  private final class ButtonSelectionListener implements SelectionListener {
    private final ExperienceCharmTreatment data;

    private ButtonSelectionListener(ExperienceCharmTreatment data) {
      this.data = data;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
      charmPreferences.setExperienceTreatment(data);
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
      widgetSelected(e);
    }
  }

  private ICharmPreferenceStore charmPreferences;
  private final List<Button> allButtons = new ArrayList<Button>();

  @Override
  protected Control createContents(Composite parent) {
    charmPreferences = createPreferences();
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout());
    new Label(composite, SWT.NONE).setText(Messages.CharmPreferencePage_Intro);
    createButton(composite, ExperienceCharmTreatment.Remember, Messages.CharmPreferencePage_Remember);
    createButton(composite, ExperienceCharmTreatment.Forget, Messages.CharmPreferencePage_Forget);
    new Label(composite, SWT.NONE).setText(Messages.CharmPreferencePage_Explanation);
    return composite;
  }

  protected ICharmPreferenceStore createPreferences() {
    return new CharmPreferences(getPreferenceStore());
  }

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return CharmCharacterPlugin.getDefaultInstance().getPreferenceStore();
  }

  private Button createButton(Composite composite, final ExperienceCharmTreatment data, String text) {
    Button button = new Button(composite, SWT.RADIO);
    button.addSelectionListener(new ButtonSelectionListener(data));
    button.setData(data);
    button.setText(text);
    button.setSelection(charmPreferences.getExperienceTreatment() == data);
    allButtons.add(button);
    return button;
  }

  @Override
  public void init(IWorkbench workbench) {
    // nothing to do
  }

  @Override
  public boolean performOk() {
    charmPreferences.commitChanges();
    return super.performOk();
  }

  @Override
  protected void performDefaults() {
    super.performDefaults();
    charmPreferences.restoreDefaults();
    adjustButtons();
  }

  private void adjustButtons() {
    ExperienceCharmTreatment experienceTreatment = charmPreferences.getExperienceTreatment();
    for (Button button : allButtons) {
      button.setSelection(button.getData() == experienceTreatment);
    }
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}