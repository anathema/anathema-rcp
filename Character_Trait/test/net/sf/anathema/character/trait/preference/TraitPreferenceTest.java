package net.sf.anathema.character.trait.preference;

import static org.junit.Assert.*;

import net.sf.anathema.character.trait.preference.internal.TraitPreferences;

import org.easymock.EasyMock;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.junit.Before;
import org.junit.Test;

public class TraitPreferenceTest {

  private static final String PROP_NAME = "trait.experienceTreatment"; //$NON-NLS-1$
  private static final String ADJUST_TO_CREATION = "AdjustToCreation"; //$NON-NLS-1$
  private IPreferenceStore preferenceStore;
  private TraitPreferences preferences;

  @Before
  public void createPreferences() throws Exception {
    this.preferenceStore = new PreferenceStore();
    this.preferences = new TraitPreferences(preferenceStore);
  }

  @Test
  public void storeSetExperienceTreatmentOnCommit() throws Exception {
    preferences.setExperienceTreatment(ExperienceTraitTreatment.AdjustToCreation);
    preferences.commitChanges();
    assertEquals(ADJUST_TO_CREATION, preferenceStore.getString(PROP_NAME));
  }

  @Test
  public void noStorageOFSetExperienceTreamentWithoutCommit() throws Exception {
    preferences.setExperienceTreatment(ExperienceTraitTreatment.AdjustToCreation);
    assertEquals("", preferenceStore.getString(PROP_NAME)); //$NON-NLS-1$
  }

  @Test
  public void noChangeOnStoreOnCommitWithoutPriorSet() throws Exception {
    preferenceStore.setValue(PROP_NAME, "notToChangeHasae"); //$NON-NLS-1$ 
    preferences.commitChanges();
    assertEquals("notToChangeHasae", preferenceStore.getString(PROP_NAME)); //$NON-NLS-1$
  }

  @Test
  public void onlyOnePropertyChangeEventForRepeatingCommitsOfSameValue() throws Exception {
    IPropertyChangeListener propertyChangeListener = EasyMock.createMock(IPropertyChangeListener.class);
    propertyChangeListener.propertyChange(EasyMock.isA(PropertyChangeEvent.class));
    EasyMock.replay(propertyChangeListener);
    preferenceStore.addPropertyChangeListener(propertyChangeListener);
    preferences.setExperienceTreatment(ExperienceTraitTreatment.AdjustToCreation);
    preferences.commitChanges();
    preferences.commitChanges();
    assertEquals(ADJUST_TO_CREATION, preferenceStore.getString(PROP_NAME));
    EasyMock.verify(propertyChangeListener);
  }

  @Test
  public void contentOfStoreIsReturnedWithoutSettingAValue() throws Exception {
    preferenceStore.setValue(PROP_NAME, ADJUST_TO_CREATION);
    assertEquals(ExperienceTraitTreatment.AdjustToCreation, preferences.getExperienceTreatment());
  }

  @Test
  public void contentIsReturnedAfterItIsExplicitlySpecified() throws Exception {
    preferenceStore.setValue(PROP_NAME, ADJUST_TO_CREATION);
    preferences.setExperienceTreatment(ExperienceTraitTreatment.LeaveUnchanged);
    assertEquals(ExperienceTraitTreatment.LeaveUnchanged, preferences.getExperienceTreatment());
  }

  @Test
  public void contentOfStoreDefaultValueIsReturnedIfNoValueIsFound() throws Exception {
    preferenceStore.setDefault(PROP_NAME, ADJUST_TO_CREATION);
    assertEquals(ExperienceTraitTreatment.AdjustToCreation, preferences.getExperienceTreatment());
  }

  @Test
  public void initializedLeaveUnchangedAsDefaultValueForExperiencedTraitTreatment() throws Exception {
    preferences.initializeDefaults();
    assertEquals(ExperienceTraitTreatment.LeaveUnchanged, preferences.getExperienceTreatment());
    assertEquals("LeaveUnchanged", preferenceStore.getDefaultString(PROP_NAME)); //$NON-NLS-1$
  }

  @Test
  public void restoresLeaveUnchangedOnRestoreDefaults() throws Exception {
    preferences.initializeDefaults();
    preferences.setExperienceTreatment(ExperienceTraitTreatment.AdjustToCreation);
    preferences.commitChanges();
    preferences.restoreDefaults();
    assertEquals(ExperienceTraitTreatment.LeaveUnchanged, preferences.getExperienceTreatment());
    assertEquals("LeaveUnchanged", preferenceStore.getString(PROP_NAME)); //$NON-NLS-1$
  }
}