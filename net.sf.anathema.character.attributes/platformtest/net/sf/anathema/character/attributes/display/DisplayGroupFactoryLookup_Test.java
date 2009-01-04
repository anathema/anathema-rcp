package net.sf.anathema.character.attributes.display;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.attributes.util.AttributeDisplayGroupFactory;
import net.sf.anathema.character.trait.display.DisplayFactoryLookup;
import net.sf.anathema.character.trait.display.IDisplayGroupFactory;

import org.junit.Test;

public class DisplayGroupFactoryLookup_Test {

  @Test
  public void returnsRegisteredFactoryForModel() throws Exception {
    IDisplayGroupFactory factory = new DisplayFactoryLookup().getFor(IAttributesPluginConstants.MODEL_ID);
    assertThat(factory, is(instanceOf(AttributeDisplayGroupFactory.class)));
  }
}