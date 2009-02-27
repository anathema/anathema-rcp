package net.sf.anathema.character.trait.favorization;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.template.FavorizationTemplateExtensionPoint;
import net.sf.anathema.character.trait.template.IFavorizationTemplateMap;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class FavorizationTemplateExtensionPoint_RequiredTest {

  private static final String REQUIRED_FAVORED_ID = "requiredFavored"; //$NON-NLS-1$
  private static final int CONFIGURED_FAVORED_COUNT = 2;
  private static final String TEMPLATE_ID = "testTemplateId"; //$NON-NLS-1$
  private static final String MODEL_ID = "testModelId"; //$NON-NLS-1$
  private IFavorizationTemplateMap provider;

  @Before
  public void createProvider() throws Exception {
    IExtensionElement element = FavorizationElementObjectMother.create(
        TEMPLATE_ID,
        MODEL_ID,
        CONFIGURED_FAVORED_COUNT,
        REQUIRED_FAVORED_ID);
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(element);
    provider = new FavorizationTemplateExtensionPoint(MODEL_ID, ExtensionObjectMother.createExtensionPoint(pluginExtension));
  }

  @Test
  public void marksRequiredFavoredNotAsRequiredForOtherTemplateId() throws Exception {
    IFavorizationTemplate template = provider.getTemplate("otherTemplateId"); //$NON-NLS-1$
    assertFalse(template.isRequiredFavored(new Identificate(REQUIRED_FAVORED_ID)));
  }

  @Test
  public void marksRequiredFavoredAsRequiredForTemplateId() throws Exception {
    IFavorizationTemplate template = provider.getTemplate(TEMPLATE_ID);
    assertTrue(template.isRequiredFavored(new Identificate(REQUIRED_FAVORED_ID)));
  }
}