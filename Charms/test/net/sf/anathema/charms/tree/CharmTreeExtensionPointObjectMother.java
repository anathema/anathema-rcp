package net.sf.anathema.charms.tree;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

public class CharmTreeExtensionPointObjectMother {

  public static IExtensionElement createTreeElement(String treeId, String charmId) throws ExtensionException {
    IExtensionElement charm = createExtensionElementWithAttributes(new MockName("charm"), //$NON-NLS-1$
        new MockStringAttribute("id", charmId)); //$NON-NLS-1$
    return createExtensionElementWithAttributes(new MockName("treepart"), //$NON-NLS-1$
        new MockStringAttribute("treeReference", treeId), //$NON-NLS-1$
        new MockChildren(charm));
  }
}