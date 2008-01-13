package net.sf.anathema.character.caste.model;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.IMockProp;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockImageDescriptorAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.lib.util.Identificate;

import org.eclipse.jface.resource.ImageDescriptor;
import org.junit.Test;

public class CasteTest {

  @Test
  public void configuredIdIsReturned() throws Exception {
    IMockProp idAttribute = new MockStringAttribute("casteId", "It's Me"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals("It's Me", new Caste(ExtensionObjectMother.createExtensionElementWithAttributes(idAttribute)).getId()); //$NON-NLS-1$
  }

  @Test(expected = IllegalStateException.class)
  public void throwsExceptionForUnconfiguredId() throws Exception {
    new Caste(ExtensionObjectMother.createExtensionElementWithAttributes()).getId();
  }

  @Test
  public void configuredPrintNameIsReturned() throws Exception {
    IMockProp nameAttribute = new MockStringAttribute("printName", "B�ser Wolf"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals(
        "B�ser Wolf", new Caste(ExtensionObjectMother.createExtensionElementWithAttributes(nameAttribute)).getPrintName()); //$NON-NLS-1$
  }

  @Test
  public void configuredImageDescriptorIsReturned() throws Exception {
    ImageDescriptor imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
    IMockProp nameAttribute = new MockImageDescriptorAttribute("icon", imageDescriptor); //$NON-NLS-1$
    assertSame(
        imageDescriptor,
        new Caste(ExtensionObjectMother.createExtensionElementWithAttributes(nameAttribute)).getIcon());
  }

  @Test
  public void doesNotSupportUnSupportedTrait() throws Exception {
    MockStringAttribute traitIdAttribute = new MockStringAttribute("traitId", "supportedTrait"); //$NON-NLS-1$ //$NON-NLS-2$
    assertFalse(new Caste(ExtensionObjectMother.createExtensionElementWithAttributes(new MockChildren(
        ExtensionObjectMother.createExtensionElementWithAttributes(traitIdAttribute)))).supportsTrait(new Identificate(
        "unsupportedTrait"))); //$NON-NLS-1$
  }

  @Test
  public void supportsSupportedTrait() throws Exception {
    MockStringAttribute traitIdAttribute = new MockStringAttribute("traitId", "supportedTrait"); //$NON-NLS-1$ //$NON-NLS-2$
    assertTrue(new Caste(ExtensionObjectMother.createExtensionElementWithAttributes(new MockChildren(
        ExtensionObjectMother.createExtensionElementWithAttributes(traitIdAttribute)))).supportsTrait(new Identificate(
        "supportedTrait"))); //$NON-NLS-1$
  }
}