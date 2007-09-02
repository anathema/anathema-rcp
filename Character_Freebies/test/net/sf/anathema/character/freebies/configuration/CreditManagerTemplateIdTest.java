package net.sf.anathema.character.freebies.configuration;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.template.ICharacterTemplate;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class CreditManagerTemplateIdTest {
  private static final String CREDIT_ID = "creditId"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_ID = "net.sf.anathema.character.freebies.credits"; //$NON-NLS-1$
  private static final String TEMPLATE_ID = "template"; //$NON-NLS-1$
  private static final ICharacterId CHARACTER_ID = CharacterObjectMother.createCharacter(TEMPLATE_ID);
  private CreditManager manager;
  private IExtensionProvider provider;

  @Before
  public void createManager() throws Exception {
    this.provider = EasyMock.createMock(IExtensionProvider.class);
    ICharacterTemplateProvider templateProvider = EasyMock.createMock(ICharacterTemplateProvider.class);
    ICharacterTemplate template = EasyMock.createMock(ICharacterTemplate.class);
    EasyMock.expect(templateProvider.getTemplate(CHARACTER_ID)).andReturn(template);
    EasyMock.expect(template.getId()).andReturn(TEMPLATE_ID);
    EasyMock.replay(template, templateProvider);
    this.manager = new CreditManager(provider, templateProvider);
  }

  @Test
  public void hasNoCreditsIfNoExtensionsAreRegistered() {
    EasyMock.expect(provider.getExtensions(EXTENSION_POINT_ID)).andReturn(new IPluginExtension[0]);
    EasyMock.replay(provider);
    assertFalse(manager.hasCredit(TEMPLATE_ID, CREDIT_ID));
  }
}
