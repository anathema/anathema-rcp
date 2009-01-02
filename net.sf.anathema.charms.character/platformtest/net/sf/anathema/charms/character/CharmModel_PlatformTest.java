package net.sf.anathema.charms.character;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.charms.character.model.ICharmModel;

import org.junit.Test;

public class CharmModel_PlatformTest {

  @Test
  public void modelIsRegisteredForDefinedConstant() throws Exception {
    assertNotNull(new ModelExtensionPoint().getModelElement(ICharmModel.MODEL_ID));
  }
}