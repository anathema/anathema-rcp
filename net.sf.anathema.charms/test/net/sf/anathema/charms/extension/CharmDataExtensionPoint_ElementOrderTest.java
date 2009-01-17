package net.sf.anathema.charms.extension;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockNamedChild;
import net.sf.anathema.basics.eclipse.extension.fake.MockNamedChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class CharmDataExtensionPoint_ElementOrderTest {
  private CharmDataExtensionPoint point;

  @Before
  public void createExtensionPoint() throws Exception {
    IExtensionElement charm = createCharmWithSourceElementFirst();
    IPluginExtension extension = ExtensionObjectMother.createPluginExtension(charm);
    IExtensionPoint extensionPoint = ExtensionObjectMother.createExtensionPoint(extension);
    point = new CharmDataExtensionPoint(extensionPoint);
  }

  @Test
  public void doesNotCareAboutElementOrder() throws Exception {
    ICharmId charmId = new CharmId("id.{0}", "trait");
    CharmDto actualDto = point.getData(charmId);
    assertThat(actualDto.type, is("permanent"));
  }

  private IExtensionElement createCharmWithSourceElementFirst() throws ExtensionException {
    IExtensionElement type = ExtensionObjectMother.createExtensionElementWithAttributes(new MockName("permanent"));
    IExtensionElement keywords = ExtensionObjectMother.createExtensionElementWithAttributes(new MockStringAttribute(
        "value",
        "Combo-OK"));
    IExtensionElement source = ExtensionObjectMother.createExtensionElementWithAttributes(new MockStringAttribute(
        "source",
        "Ex2"));
    MockStringAttribute id = new MockStringAttribute("charmId", "id.{0}");
    return ExtensionObjectMother.createExtensionElementWithAttributes(
        id,
        new MockNamedChildren("source", source),
        new MockChildren(source),
        new MockChildren(type),
        new MockNamedChild("permanent", type),
        new MockNamedChildren("keyword", keywords));
  }
}