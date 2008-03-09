package net.sf.anathema.character.trait.model;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.IMockProp;
import net.sf.anathema.basics.eclipse.extension.fake.MockIntegerAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockNamedChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

import org.junit.Before;
import org.junit.Test;

public class TraitTemplateFactoryTest {

  private static final String MINIMAL_TRAIT = "minimalTrait"; //$NON-NLS-1$
  private static final String TEMPLATE_ID = "myTemplateId"; //$NON-NLS-1$
  private static final int DEFAULT_MINIMAL_VALUE = 2;
  private TraitTemplateFactory factory;

  @Before
  public void createFactory() throws ExtensionException {
    IMockProp traitIdAttribute = new MockStringAttribute("traitId", MINIMAL_TRAIT); //$NON-NLS-1$
    IMockProp minimalValueAttribute = new MockIntegerAttribute("value", 1); //$NON-NLS-1$
    IExtensionElement minimalElement = ExtensionObjectMother.createExtensionElementWithAttributes(
        traitIdAttribute,
        minimalValueAttribute);
    IExtensionElement templateElement = ExtensionObjectMother.createExtensionElementWithAttributes(
        new MockStringAttribute("characterTemplateId", TEMPLATE_ID), new MockNamedChildren("minimalValue", minimalElement)); //$NON-NLS-1$ //$NON-NLS-2$
    factory = new TraitTemplateFactory(
        DEFAULT_MINIMAL_VALUE,
        TEMPLATE_ID,
        ExtensionObjectMother.createPluginExtension(templateElement));
  }

  @Test
  public void createsTemplateWithGivenDefaultMinimalValue() throws Exception {
    assertEquals(DEFAULT_MINIMAL_VALUE, factory.getTraitTemplate("ohh duu trait").getMinimalValue()); //$NON-NLS-1$
  }

  @Test
  public void createsTemplateWithConfiguredMinimalValue() throws Exception {
    assertEquals(1, factory.getTraitTemplate(MINIMAL_TRAIT).getMinimalValue());
  }
}