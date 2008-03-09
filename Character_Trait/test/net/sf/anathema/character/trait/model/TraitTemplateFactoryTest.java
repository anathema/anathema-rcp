package net.sf.anathema.character.trait.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TraitTemplateFactoryTest {

  @Test
  public void createsTemplateWithGivenMinimalValue() throws Exception {
    assertEquals(2, new TraitTemplateFactory(2).getTraitTemplate(null).getMinimalValue());
  }
}