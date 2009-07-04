package net.sf.anathema.charms.extension.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CharmDataExtensionPoint_ElementOrderTest {
  private final CharmDataExtensionPoint point;
  private final String charmType;

  @Parameters
  public static Collection<Object[]> charmTypes() {
    ArrayList<Object[]> list = new ArrayList<Object[]>();
    Collections.addAll(list, new Object[] { "simple" }, //$NON-NLS-1$
        new Object[] { "permanent" }, //$NON-NLS-1$
        new Object[] { "supplemental" }, //$NON-NLS-1$
        new Object[] { "reflexive" }, //$NON-NLS-1$
        new Object[] { "enchantment" }, //$NON-NLS-1$
        new Object[] { "extraaction" }); //$NON-NLS-1$
    return list;
  }

  public CharmDataExtensionPoint_ElementOrderTest(String type) throws ExtensionException {
    this.charmType = type;
    IExtensionElement charm = createCharmWithSourceElementFirst();
    IPluginExtension extension = ExtensionObjectMother.createPluginExtension(charm);
    IExtensionPoint extensionPoint = ExtensionObjectMother.createExtensionPoint(extension);
    point = new CharmDataExtensionPoint(extensionPoint);
  }

  @Test
  public void recognizesCharmType() throws Exception {
    ICharmId charmId = new CharmId("id.{0}", "trait"); //$NON-NLS-1$ //$NON-NLS-2$
    CharmDto actualDto = point.getData(charmId);
    assertThat(actualDto.type, is(charmType));
  }

  private IExtensionElement createCharmWithSourceElementFirst() throws ExtensionException {
    IExtensionElement innerTypeElement = ExtensionObjectMother.createExtensionElement(new MockName(charmType));
    IExtensionElement outerTypeElement = ExtensionObjectMother.createExtensionElement(new MockName("type"), new MockChildren(innerTypeElement));
    IExtensionElement keywords = ExtensionObjectMother.createExtensionElement(new MockStringAttribute(
        "value", //$NON-NLS-1$
        "Combo-OK")); //$NON-NLS-1$
    IExtensionElement source = ExtensionObjectMother.createExtensionElement(new MockStringAttribute(
        "source", //$NON-NLS-1$
        "Ex2")); //$NON-NLS-1$
    MockStringAttribute id = new MockStringAttribute("charmId", "id.{0}"); //$NON-NLS-1$ //$NON-NLS-2$
    return ExtensionObjectMother.createExtensionElement(id, new MockNamedChildren("source", source), //$NON-NLS-1$
        new MockChildren(source, outerTypeElement),
        new MockNamedChild("type", outerTypeElement),
        new MockNamedChildren("keyword", keywords)); //$NON-NLS-1$
  }
}