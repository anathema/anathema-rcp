package net.sf.anathema.character.freebies.configuration;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.FakeExtensionElement;
import net.sf.anathema.character.core.fake.TemplateProviderObjectMother;
import net.sf.anathema.character.core.model.ICharacterId;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CreditManagerTest {

  private static final String ELEMENT_CREDIT = "credit"; //$NON-NLS-1$
  private static final String CREDIT_ID = "creditId"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_ID = "net.sf.anathema.character.freebies.credits"; //$NON-NLS-1$
  private static final String TEMPLATE_ID = "template"; //$NON-NLS-1$
  private ICharacterId characterId;
  private CreditManager manager;

  @Parameters
  public static Collection<Object[]> createParameters() {
    return new ArrayList<Object[]>() {
      {
        add(new Object[] { "blafasel", false, 0 }); //$NON-NLS-1$
        add(new Object[] { CREDIT_ID, true, 1 });
      }
    };
  }

  private final String creditId;
  private final boolean expectedAvailability;
  private final int expectedCredit;

  public CreditManagerTest(String creditId, boolean expectedAvailability, int expectedCredit) {
    this.creditId = creditId;
    this.expectedAvailability = expectedAvailability;
    this.expectedCredit = expectedCredit;
  }

  @Before
  public void createManager() throws Exception {
    this.characterId = TemplateProviderObjectMother.createCharacterId(TEMPLATE_ID);
    IExtensionProvider provider = EasyMock.createMock(IExtensionProvider.class);
    this.manager = new CreditManager(provider, TemplateProviderObjectMother.createTemplateProvider(
        characterId,
        TEMPLATE_ID));
    FakeExtensionElement element = createCreditsElement();
    createExtension(element, provider);
  }

  @Test
  public void assertAvailability() {
    assertEquals(expectedAvailability, manager.hasCredit(TEMPLATE_ID, CREDIT_ID));
  }

  @Test
  public void assertAmount() {
    assertEquals(expectedCredit, manager.getCredit(characterId, CREDIT_ID));
  }

  private void createExtension(FakeExtensionElement element, IExtensionProvider provider) {
    IExtensionElement[] elements = new IExtensionElement[] { element };
    IPluginExtension extension = EasyMock.createMock(IPluginExtension.class);
    EasyMock.expect(extension.getElements()).andReturn(elements).anyTimes();
    IPluginExtension[] extensions = new IPluginExtension[] { extension };
    EasyMock.expect(provider.getExtensions(EXTENSION_POINT_ID)).andReturn(extensions).anyTimes();
    EasyMock.replay(provider, extension);
  }

  private FakeExtensionElement createCreditsElement() {
    FakeExtensionElement element = new FakeExtensionElement();
    element.addAttribute("templateId", TEMPLATE_ID); //$NON-NLS-1$
    FakeExtensionElement creditElement = new FakeExtensionElement();
    creditElement.addAttribute("id", creditId); //$NON-NLS-1$
    creditElement.addAttribute("value", 1); //$NON-NLS-1$
    element.addElement(ELEMENT_CREDIT, creditElement);
    return element;
  }
}