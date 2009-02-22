package net.sf.anathema.charms.extension;

import static net.disy.commons.core.util.ArrayUtilities.*;
import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.IMockProp;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockNamedChild;

public class DurationElementObjectMother {

  static IExtensionElement createAdditiveDuration(IMockProp mockChildren) throws ExtensionException {
    IExtensionElement addition = DurationElementObjectMother.createNamedElement("addition", mockChildren);
    return DurationElementObjectMother.createDuration(
        new MockNamedChild("addition", addition), new MockChildren(addition)); //$NON-NLS-1$
  }

  static IExtensionElement createDuration(IMockProp... children) throws ExtensionException {
    return createExtensionElement(children);
  }

  static IExtensionElement createNamedElement(String name, IMockProp... children) throws ExtensionException {
    MockName elementName = new MockName(name);
    IMockProp[] props = concat(elementName, children, IMockProp.class);
    return createExtensionElement(props);
  }

}
