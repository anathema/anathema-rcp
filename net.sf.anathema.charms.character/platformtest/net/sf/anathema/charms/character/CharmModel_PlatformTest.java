package net.sf.anathema.charms.character;

import static org.junit.Assert.*;

import net.sf.anathema.character.core.model.ModelExtensionPoint;

import org.junit.Test;

public class CharmModel_PlatformTest {

  @Test
  public void modelIsRegisteredForDefinedConstant() throws Exception {
    assertNotNull(new ModelExtensionPoint().getModelElement(CharmModel.ID));
  }
}