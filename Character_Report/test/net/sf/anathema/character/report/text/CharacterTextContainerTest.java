package net.sf.anathema.character.report.text;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.IMockProp;
import net.sf.anathema.basics.eclipse.extension.fake.MockExecutableExtensionAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

import org.junit.Before;
import org.junit.Test;

public class CharacterTextContainerTest {

  private static final String ID = "tumid"; //$NON-NLS-1$
  private ICharacterText text;
  private CharacterTextContainer textContainer;

  @Before
  public void createContainer() throws Exception {
    text = createMock(ICharacterText.class);
    IMockProp idAttribute = new MockStringAttribute("id", ID); //$NON-NLS-1$
    IMockProp classAttribute = new MockExecutableExtensionAttribute<ICharacterText>("class", ICharacterText.class, text); //$NON-NLS-1$
    IExtensionElement element = ExtensionObjectMother.createExtensionElementWithAttributes(idAttribute, classAttribute);
    IPluginExtension extension = ExtensionObjectMother.createPluginExtension(element);
    textContainer = new CharacterTextContainer(extension);
  }
  
  @Test
  public void findsNoTextForUnknownId() throws Exception {
    assertNull(textContainer.getText("MichGibtsNichtTum")); //$NON-NLS-1$
  }
  
  @Test
  public void findsTextForId() throws Exception {
    assertEquals(text, textContainer.getText(ID));
  }
}