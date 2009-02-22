package net.sf.anathema.charms.tree;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

public class CharmPrerequisiteTransformer implements ITransformer<String, IExtensionElement> {

  @Override
  public IExtensionElement transform(String charmId) {
    try {
      return ExtensionObjectMother.createExtensionElement(
          new MockName("charmPrerequisite"), //$NON-NLS-1$
          new MockStringAttribute("charmId", charmId)); //$NON-NLS-1$
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}