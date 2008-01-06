package net.sf.anathema.character.caste.model;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.IMockProp;
import net.sf.anathema.basics.eclipse.extension.fake.MockImageDescriptorAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

import org.eclipse.jface.resource.ImageDescriptor;
import org.junit.Test;

public class CasteTest {

  @Test
  public void configuredIdIsReturned() throws Exception {
    IMockProp idAttribute = new MockStringAttribute("casteId", "It's Me"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals("It's Me", new Caste(ExtensionObjectMother.createExtensionElementWithAttributes(idAttribute)).getId()); //$NON-NLS-1$
  }
  
  @Test(expected=IllegalStateException.class)
  public void throwsExceptionForUnconfiguredId() throws Exception {
    new Caste(ExtensionObjectMother.createExtensionElementWithAttributes()).getId();
  }
  
  @Test
  public void configuredPrintNameIsReturned() throws Exception {
    IMockProp nameAttribute = new MockStringAttribute("printName", "Böser Wolf"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals("Böser Wolf", new Caste(ExtensionObjectMother.createExtensionElementWithAttributes(nameAttribute)).getPrintName()); //$NON-NLS-1$
  }
  
  @Test
  public void configuredImageDescriptorIsReturned() throws Exception {
    ImageDescriptor imageDescriptor = ImageDescriptor.getMissingImageDescriptor();;
    IMockProp nameAttribute = new MockImageDescriptorAttribute("icon", imageDescriptor); //$NON-NLS-1$
    assertSame(imageDescriptor, new Caste(ExtensionObjectMother.createExtensionElementWithAttributes(nameAttribute)).getIcon());
  }
}