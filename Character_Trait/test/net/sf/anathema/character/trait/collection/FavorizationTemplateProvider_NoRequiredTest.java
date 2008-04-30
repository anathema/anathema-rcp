package net.sf.anathema.character.trait.collection;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;

import org.junit.Before;
import org.junit.Test;

public class FavorizationTemplateProvider_NoRequiredTest {

  private static final int CONFIGURED_FAVORED_COUNT = 2;
  private static final String TEMPLATE_ID = "testTemplateId"; //$NON-NLS-1$
  private static final String MODEL_ID = "testModelId"; //$NON-NLS-1$
  private FavorizationTemplateProvider provider;

  @Before
  public void createProvider() throws Exception {
    IExtensionElement element = FavorizationElementObjectMother.create(TEMPLATE_ID, MODEL_ID, CONFIGURED_FAVORED_COUNT);
    provider = new FavorizationTemplateProvider(MODEL_ID, ExtensionObjectMother.createPluginExtension(element));
  }

  @Test
  public void providesNoFavoredCountIfNoConfigurationIsFoundForTemplateId() throws Exception {
    IFavorizationTemplate template = provider.getFavorizationTemplate("otherTemplateId"); //$NON-NLS-1$
    assertEquals(0, template.getFavorizationCount());
  }

  @Test
  public void readsFavoredCountCorrectly() throws Exception {
    IFavorizationTemplate template = provider.getFavorizationTemplate(TEMPLATE_ID);
    assertEquals(CONFIGURED_FAVORED_COUNT, template.getFavorizationCount());
  }
}