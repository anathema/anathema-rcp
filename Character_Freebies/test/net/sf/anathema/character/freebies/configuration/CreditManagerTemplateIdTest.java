package net.sf.anathema.character.freebies.configuration;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.fake.TemplateProviderObjectMother;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class CreditManagerTemplateIdTest {
  private static final String CREDIT_ID = "creditId"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_ID = "net.sf.anathema.character.freebies.credits"; //$NON-NLS-1$
  private static final String TEMPLATE_ID = "template"; //$NON-NLS-1$
  private CreditManager manager;
  private IExtensionProvider provider;

  @Before
  public void createManager() throws Exception {
    this.provider = EasyMock.createMock(IExtensionProvider.class);
    this.manager = new CreditManager(provider, TemplateProviderObjectMother.createTemplateProvider(
        TemplateProviderObjectMother.createCharacterId(TEMPLATE_ID),
        TEMPLATE_ID));
  }

  @Test
  public void hasNoCreditsIfNoExtensionsAreRegistered() {
    EasyMock.expect(provider.getExtensions(EXTENSION_POINT_ID)).andReturn(new IPluginExtension[0]);
    EasyMock.replay(provider);
    assertFalse(manager.hasCredit(TEMPLATE_ID, CREDIT_ID));
  }
}