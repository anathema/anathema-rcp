package net.sf.anathema.character.trait.favorization;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.template.FavorizationTemplateExtensionPoint;
import net.sf.anathema.character.trait.template.IFavorizationTemplateMap;

import org.junit.Before;
import org.junit.Test;

public class FavorizationTemplateExtensionPoint_NoRequiredTest {

  private static final int CONFIGURED_FAVORED_COUNT = 2;
  private static final String TEMPLATE_ID = "testTemplateId"; //$NON-NLS-1$
  private static final String MODEL_ID = "testModelId"; //$NON-NLS-1$
  private IFavorizationTemplateMap provider;

  @Before
  public void createProvider() throws Exception {
    IExtensionElement element = FavorizationElementObjectMother.create(TEMPLATE_ID, MODEL_ID, CONFIGURED_FAVORED_COUNT);
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(element);
    provider = new FavorizationTemplateExtensionPoint(MODEL_ID, ExtensionObjectMother.createExtensionPoint(pluginExtension));
  }

  @Test
  public void providesNoFavoredCountIfNoConfigurationIsFoundForTemplateId() throws Exception {
    IFavorizationTemplate template = provider.getTemplate("otherTemplateId"); //$NON-NLS-1$
    assertEquals(0, template.getAllowedFavored());
  }

  @Test
  public void readsFavoredCountCorrectly() throws Exception {
    IFavorizationTemplate template = provider.getTemplate(TEMPLATE_ID);
    assertEquals(CONFIGURED_FAVORED_COUNT, template.getAllowedFavored());
  }
}