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
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.ICharacterTypeFinder;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.freebies.virtues.internal.VirtueFreebieLimit;

import org.easymock.EasyMock;
import org.eclipse.jface.resource.ImageDescriptor;
import org.junit.Test;

@SuppressWarnings("nls")
public class VirtueFreebieLimit_Test {

  private static final class DummyCharacterType implements ICharacterType {
    private final String typename;

    private DummyCharacterType(String typename) {
      this.typename = typename;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
      throw new UnsupportedOperationException("Dummy");
    }

    @Override
    public String getTraitImageId() {
      throw new UnsupportedOperationException("Dummy");
    }

    @Override
    public String getId() {
      return typename;
    }
  }

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
    ICharacterType type = new DummyCharacterType(typename);
    EasyMock.expect(finder.getCharacterType(id)).andReturn(type);
    EasyMock.replay(finder);
    return finder;
  }
}