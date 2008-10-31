package net.sf.anathema.character.trait.collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class FavorizationTemplateProvider_RequiredTest {

  private static final String REQUIRED_FAVORED_ID = "requiredFavored"; //$NON-NLS-1$
  private static final int CONFIGURED_FAVORED_COUNT = 2;
  private static final String TEMPLATE_ID = "testTemplateId"; //$NON-NLS-1$
  private static final String MODEL_ID = "testModelId"; //$NON-NLS-1$
  private FavorizationTemplateProvider provider;

  @Before
  public void createProvider() throws Exception {
    IExtensionElement element = FavorizationElementObjectMother.create(
        TEMPLATE_ID,
        MODEL_ID,
        CONFIGURED_FAVORED_COUNT,
        REQUIRED_FAVORED_ID);
    provider = new FavorizationTemplateProvider(MODEL_ID, ExtensionObjectMother.createPluginExtension(element));
  }

  @Test
  public void marksRequiredFavoredNotAsRequiredForOtherTemplateId() throws Exception {
    IFavorizationTemplate template = provider.getFavorizationTemplate("otherTemplateId"); //$NON-NLS-1$
    assertFalse(template.isRequiredFavored(new Identificate(REQUIRED_FAVORED_ID)));
  }

  @Test
  public void marksRequiredFavoredAsRequiredForTemplateId() throws Exception {
    IFavorizationTemplate template = provider.getFavorizationTemplate(TEMPLATE_ID);
    assertTrue(template.isRequiredFavored(new Identificate(REQUIRED_FAVORED_ID)));
  }
}