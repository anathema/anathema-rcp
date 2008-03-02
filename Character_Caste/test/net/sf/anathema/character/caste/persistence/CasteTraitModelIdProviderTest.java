package net.sf.anathema.character.caste.persistence;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

import org.junit.Before;
import org.junit.Test;

public class CasteTraitModelIdProviderTest {

  private static final String MODEL_ID = "trait.model.id"; //$NON-NLS-1$
  private static final String CHARACTER_TYPE = "character.type"; //$NON-NLS-1$
  private CasteTraitModelIdProvider provider;

  @Before
  public void createProvider() throws ExtensionException {
    IExtensionElement element = createExtensionElementWithAttributes(
        new MockStringAttribute("characterType", CHARACTER_TYPE), //$NON-NLS-1$
        new MockStringAttribute("modelId", MODEL_ID)); //$NON-NLS-1$
    provider = new CasteTraitModelIdProvider(createPluginExtension(element));
  }

  @Test
  public void returnsConfiguredModelIdForCharacterType() throws Exception {
    assertEquals(MODEL_ID, provider.getTraitModelId(CHARACTER_TYPE));
  }

  @Test
  public void returnsNullModelIdForUnknownCharacterType() throws Exception {
    assertNull(provider.getTraitModelId("you.do.not.know.me")); //$NON-NLS-1$
  }
}