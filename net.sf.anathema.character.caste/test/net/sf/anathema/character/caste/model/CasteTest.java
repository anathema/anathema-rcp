package net.sf.anathema.character.caste.model;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.IMockProp;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Test;

public class CasteTest {

  @Test
  public void configuredIdIsReturned() throws Exception {
    IMockProp idAttribute = new MockStringAttribute("casteId", "It's Me"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals("It's Me", new Caste(ExtensionObjectMother.createExtensionElement(idAttribute)).getId()); //$NON-NLS-1$
  }

  @Test(expected = IllegalStateException.class)
  public void throwsExceptionForUnconfiguredId() throws Exception {
    new Caste(ExtensionObjectMother.createExtensionElement()).getId();
  }

  @Test
  public void configuredPrintNameIsReturned() throws Exception {
    IMockProp nameAttribute = new MockStringAttribute("printName", "B�ser Wolf"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals(
        "B�ser Wolf", new Caste(ExtensionObjectMother.createExtensionElement(nameAttribute)).getPrintName()); //$NON-NLS-1$
  }

  @Test
  public void doesNotSupportUnSupportedTrait() throws Exception {
    MockStringAttribute traitIdAttribute = new MockStringAttribute("traitId", "supportedTrait"); //$NON-NLS-1$ //$NON-NLS-2$
    assertFalse(new Caste(ExtensionObjectMother.createExtensionElement(new MockChildren(
        ExtensionObjectMother.createExtensionElement(traitIdAttribute)))).supportsTrait(new Identificate(
        "unsupportedTrait"))); //$NON-NLS-1$
  }

  @Test
  public void supportsSupportedTrait() throws Exception {
    MockStringAttribute traitIdAttribute = new MockStringAttribute("traitId", "supportedTrait"); //$NON-NLS-1$ //$NON-NLS-2$
    assertTrue(new Caste(ExtensionObjectMother.createExtensionElement(new MockChildren(
        ExtensionObjectMother.createExtensionElement(traitIdAttribute)))).supportsTrait(new Identificate(
        "supportedTrait"))); //$NON-NLS-1$
  }
}