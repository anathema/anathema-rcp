package net.sf.anathema.character.freebies.virtues;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockIntegerAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTypeFinder;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.fake.DummyCharacterType;
import net.sf.anathema.character.freebies.virtues.internal.VirtueFreebieLimit;

import org.easymock.EasyMock;
import org.junit.Test;

@SuppressWarnings("nls")
public class VirtueFreebieLimit_Test {

  @Test
  public void defaultsTo3() throws Exception {
    ICharacterId id = new DummyCharacterId();
    ICharacterTypeFinder finder = createCharacterTypeFinder("DragonBlood", id);
    IExtensionPoint extensionPoint = ExtensionObjectMother.createExtensionPoint();
    assertThat(new VirtueFreebieLimit(extensionPoint, finder).getFor(id), is(3));
  }

  @Test
  public void retrievesLimitFromExtensionPoint() throws Exception {
    final String typename = "Solar";
    IExtensionElement element = ExtensionObjectMother.createExtensionElement(new MockStringAttribute(
        "characterType",
        typename), new MockIntegerAttribute("limit", 4));
    IPluginExtension extension = ExtensionObjectMother.createPluginExtension(element);
    IExtensionPoint extensionPoint = ExtensionObjectMother.createExtensionPoint(extension);
    ICharacterId id = new DummyCharacterId();
    ICharacterTypeFinder finder = createCharacterTypeFinder(typename, id);
    new VirtueFreebieLimit(extensionPoint, finder).getFor(id);
  }

  private ICharacterTypeFinder createCharacterTypeFinder(final String typename, ICharacterId id) {
    ICharacterTypeFinder finder = EasyMock.createNiceMock(ICharacterTypeFinder.class);
    DummyCharacterType type = new DummyCharacterType();
    type.typeId = typename;
    EasyMock.expect(finder.getCharacterType(id)).andReturn(type);
    EasyMock.replay(finder);
    return finder;
  }
}