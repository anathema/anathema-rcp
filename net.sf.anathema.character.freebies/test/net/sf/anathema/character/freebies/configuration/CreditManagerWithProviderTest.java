package net.sf.anathema.character.freebies.configuration;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.fake.TemplateProviderObjectMother;

import org.easymock.EasyMock;
import org.junit.Test;

public class CreditManagerWithProviderTest {

  private static final String CREDIT_ID = "ourCreditId"; //$NON-NLS-1$
  private static final String TEMPLATE_ID = "ourTemplateId"; //$NON-NLS-1$

  @Test
  public void returnsCreditFromProvider() throws Exception {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    ICharacterTemplate template = TemplateProviderObjectMother.createTemplate(TEMPLATE_ID);
    ICharacterTemplateProvider provider = TemplateProviderObjectMother.createTemplateProvider(characterId, template);
    ICreditProvider creditProvider = createCreditProvder(TEMPLATE_ID, 3);
    IExtensionElement providerElement = createProviderElement(CREDIT_ID, creditProvider);
    IExtensionElement[] providerElement1 = { providerElement };
    IPluginExtension extension = ExtensionObjectMother.createPluginExtension(providerElement1);
    CreditManager creditManager = new CreditManager(provider, extension);
    assertEquals(3, creditManager.getCredit(characterId, CREDIT_ID));
  }

  private IExtensionElement createProviderElement(String creditId, ICreditProvider creditProvider)
      throws ExtensionException {
    IExtensionElement providerElement = EasyMock.createMock(IExtensionElement.class);
    EasyMock.expect(providerElement.getName()).andReturn("creditProvider"); //$NON-NLS-1$
    EasyMock.expect(providerElement.getAttribute("id")).andReturn(creditId); //$NON-NLS-1$
    EasyMock.expect(providerElement.getAttributeAsObject("class", ICreditProvider.class)).andReturn(creditProvider); //$NON-NLS-1$
    EasyMock.replay(providerElement);
    return providerElement;
  }

  private ICreditProvider createCreditProvder(String templateId, int value) {
    ICreditProvider creditProvider = EasyMock.createMock(ICreditProvider.class);
    EasyMock.expect(creditProvider.getCredit(templateId)).andReturn(value).anyTimes();
    EasyMock.replay(creditProvider);
    return creditProvider;
  }
}