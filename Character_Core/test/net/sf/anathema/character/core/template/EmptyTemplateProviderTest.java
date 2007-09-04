package net.sf.anathema.character.core.template;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.modellist.IModelListProvider;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class EmptyTemplateProviderTest {

  private CharacterTemplateProvider templateProvider;

  @Before
  public void createEmptyProvider() throws Exception {
    IModelListProvider modelListProvider = EasyMock.createMock(IModelListProvider.class);
    EasyMock.replay(modelListProvider);
    templateProvider = new CharacterTemplateProvider(new IPluginExtension[0], modelListProvider);
    EasyMock.verify(modelListProvider);
  }
  
  @Test
  public void noTemplatesProvided() throws Exception {
    assertEquals(0, templateProvider.getAllTemplates().size());
  }
}