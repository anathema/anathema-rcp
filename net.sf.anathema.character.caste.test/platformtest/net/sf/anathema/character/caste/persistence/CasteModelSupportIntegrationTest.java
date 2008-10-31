package net.sf.anathema.character.caste.persistence;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.IModelDescriptor;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.junit.Test;

public class CasteModelSupportIntegrationTest {

  @Test
  public void casteModelSupportedForSolars() throws Exception {
    assertTrue(isSupportedForTemplate("net.sf.anathema.character.template.defaultsolar")); //$NON-NLS-1$
  }

  @Test
  public void noCasteModelSupportedForMortals() throws Exception {
    assertFalse(isSupportedForTemplate("net.sf.anathema.character.template.heroicmortal")); //$NON-NLS-1$
  }

  private boolean isSupportedForTemplate(String templateId) {
    ICharacterTemplate solarTemplate = new CharacterTemplateProvider().getTemplate(templateId); 
    IModelDescriptor modelDescriptor = new ModelExtensionPoint().getModelDescriptor("net.sf.anathema.character.caste.model"); //$NON-NLS-1$
    return modelDescriptor.isSupportedBy(solarTemplate);
  }
}