package net.sf.anathema.charms.extension.data;

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
import net.sf.anathema.charms.extension.data.CharmDataExtensionPoint;
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
    ICharmId charmId = new CharmId("somecharm", "notrait"); //$NON-NLS-1$ //$NON-NLS-2$
    CharmDto actualDto = point.getData(charmId);
    assertThat(actualDto, is(nullValue()));
  }

  @Test
  public void retrievesDataByIdPattern() throws Exception {
    ICharmId charmId = new CharmId("id.{0}", "trait"); //$NON-NLS-1$ //$NON-NLS-2$
    CharmDto actualDto = point.getData(charmId);
    assertThat(actualDto.type, is("reflexive")); //$NON-NLS-1$
  }

  private IExtensionElement createCharm() throws ExtensionException {
    IExtensionElement reflexiveType = ExtensionObjectMother.createExtensionElement(new MockName(
        "reflexive")); //$NON-NLS-1$
    IExtensionElement type = ExtensionObjectMother.createExtensionElement(
        new MockName("type"), new MockChildren(reflexiveType)); //$NON-NLS-1$
    IExtensionElement keywords = ExtensionObjectMother.createExtensionElement(new MockStringAttribute(
        "value", //$NON-NLS-1$
        "Combo-OK")); //$NON-NLS-1$
    IExtensionElement source = ExtensionObjectMother.createExtensionElement(new MockStringAttribute(
        "source", //$NON-NLS-1$
        "Ex2")); //$NON-NLS-1$
    MockStringAttribute id = new MockStringAttribute("charmId", "id.{0}"); //$NON-NLS-1$ //$NON-NLS-2$
    return ExtensionObjectMother.createExtensionElement(
        id,
        new MockChildren(type),
        new MockNamedChild("type", type), //$NON-NLS-1$
        new MockNamedChildren("keyword", keywords), //$NON-NLS-1$
        new MockNamedChildren("source", source)); //$NON-NLS-1$
  }
}