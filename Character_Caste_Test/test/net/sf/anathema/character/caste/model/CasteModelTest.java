package net.sf.anathema.character.caste.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CasteModelTest {

  private CasteModel casteModel;

  @Before
  public void createCleanModel() throws Exception {
    casteModel = new CasteModel(new CasteTemplate("Egal"));
    casteModel.setClean();
  }

  @Test
  public void isDirtyAfterSettingCaste() throws Exception {
    casteModel.setCaste(casteModel.getOptions()[0]);
    assertTrue(casteModel.isDirty());
  }
}