package net.sf.anathema.charms.tree;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

public class CharmTreeExtensionPointObjectMother {

  public static IExtensionElement createTreeElement(String treeId, String... charmIds) throws ExtensionException {
    return createTree(treeId, ArrayUtilities.transform(charmIds, IExtensionElement.class, new CharmTransformer()));
  }

  public static IExtensionElement createTree(String treeId, IExtensionElement... charms) throws ExtensionException {
    return createExtensionElementWithAttributes(new MockName("treepart"), //$NON-NLS-1$
        new MockStringAttribute("treeReference", treeId), //$NON-NLS-1$
        new MockChildren(charms));
  }

  public static IExtensionElement createCharm(String charmId, String... prerequisites) throws ExtensionException {
    IExtensionElement[] children = ArrayUtilities.transform(
        prerequisites,
        IExtensionElement.class,
        new CharmPrerequisiteTransformer());
    return createExtensionElementWithAttributes(new MockName("charm"), //$NON-NLS-1$
        new MockStringAttribute("id", charmId),
        new MockChildren(children));
  }
}