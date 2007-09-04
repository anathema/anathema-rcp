package net.sf.anathema.character.core.template;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.modellist.IModelList;
import net.sf.anathema.character.core.modellist.ModelList;

import org.junit.Before;
import org.junit.Test;

public class CharacterTemplateTest {

  private CharacterTemplate template;

  @Before
  public void createTemplate() {
    template = new CharacterTemplate("testTemplateId"); //$NON-NLS-1$
    template.addModelList(createModelList("supportedModelId")); //$NON-NLS-1$
    template.addModelList(createModelList("otherSupportedModelId")); //$NON-NLS-1$
  }

  private IModelList createModelList(String modelId) {
    ModelList modelList = new ModelList();
    modelList.addModelId(modelId);
    return modelList;
  }

  @Test
  public void hasId() throws Exception {
    assertEquals("testTemplateId", template.getId()); //$NON-NLS-1$
  }

  @Test
  public void doesNotSupportUnsupportedModel() throws Exception {
    assertFalse(template.supportsModel("unsupportedModelId")); //$NON-NLS-1$
  }

  @Test
  public void supportsSupportedModel() throws Exception {
    assertTrue(template.supportsModel("supportedModelId")); //$NON-NLS-1$
  }

  @Test
  public void supportsOtherSupportedModel() throws Exception {
    assertTrue(template.supportsModel("otherSupportedModelId")); //$NON-NLS-1$
  }
}