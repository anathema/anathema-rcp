package net.sf.anathema.character.core.resource;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;

import org.junit.Test;

public class CharacterDisplayNameProvider_FallbackTest {

  @Test
  public void returnsTemplateNameAsFallback() throws Exception {
    String name = new CharacterDisplayNameProvider(
        ResourceObjectMother.createNamedContainer("Narf"), new FakeCharacterTemplateProvider()).getFallbackName(); //$NON-NLS-1$
    assertEquals("Test", name); //$NON-NLS-1$
  }
}