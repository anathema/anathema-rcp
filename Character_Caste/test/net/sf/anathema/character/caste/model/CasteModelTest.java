package net.sf.anathema.character.caste.model;

import static org.junit.Assert.*;
import net.sf.anathema.character.caste.CasteObjectMother;

import org.junit.Before;
import org.junit.Test;

public class CasteModelTest {

  private CasteModel casteModel;

  @Before
  public void createCleanModel() throws Exception {
    casteModel = new CasteModel(new CasteTemplate(null, CasteObjectMother.createCaste("Egal", "Egal"))); //$NON-NLS-1$ //$NON-NLS-2$
    casteModel.setClean();
  }

  @Test
  public void isDirtyAfterSettingCasteByPrintName() throws Exception {
    casteModel.setCaste(casteModel.getOptions()[0]);
    assertTrue(casteModel.isDirty());
  }
}