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
import net.sf.anathema.basics.eclipse.extension.fake.MockNamedChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class CharmDataExtensionPoint_Test {

  private CharmDataExtensionPoint point;

  @Before
  public void createExtensionPoint() throws Exception {
    IExtensionElement charm = createCharm();
    IPluginExtension extension = ExtensionObjectMother.createPluginExtension(charm);
    IExtensionPoint extensionPoint = ExtensionObjectMother.createExtensionPoint(extension);
    point = new CharmDataExtensionPoint(extensionPoint);
  }

  @Test
  public void returnsNullForUnknownCharm() throws Exception {
    ICharmId charmId = new CharmId("somecharm", "notrait");
    CharmDto actualDto = point.getData(charmId);
    assertThat(actualDto, is(nullValue()));
  }

  @Test
  public void retrievesDataByIdPattern() throws Exception {
    ICharmId charmId = new CharmId("id.{0}", "trait");
    CharmDto actualDto = point.getData(charmId);
    assertThat(actualDto.type, is("reflexive"));
  }

  private IExtensionElement createCharm() throws ExtensionException {
    IExtensionElement type = ExtensionObjectMother.createExtensionElementWithAttributes(new MockName("reflexive"));
    IExtensionElement keywords = ExtensionObjectMother.createExtensionElementWithAttributes(new MockStringAttribute(
        "value",
        "Combo-OK"));
    IExtensionElement source = ExtensionObjectMother.createExtensionElementWithAttributes(new MockStringAttribute(
        "source",
        "Ex2"));
    MockStringAttribute id = new MockStringAttribute("charmId", "id.{0}");
    IExtensionElement charm = ExtensionObjectMother.createExtensionElementWithAttributes(
        id,
        new MockChildren(type),
        new MockNamedChildren("keyword", keywords),
        new MockNamedChildren("source", source));
    return charm;
  }
}