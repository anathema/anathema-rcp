package net.sf.anathema.character.core.template;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;

import org.junit.Before;
import org.junit.Test;

public class EmptyTemplateProviderTest {

  private CharacterTemplateProvider templateProvider;

  @Before
  public void createEmptyProvider() throws Exception {
    templateProvider = new CharacterTemplateProvider(new IPluginExtension[0]);
  }
  
  @Test
  public void noTemplatesProvided() throws Exception {
    assertEquals(0, templateProvider.getAllTemplates().size());
  }
}