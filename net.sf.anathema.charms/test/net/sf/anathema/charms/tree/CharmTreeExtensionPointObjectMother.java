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
    return createTreePart(treeId, ArrayUtilities.transform(charmIds, IExtensionElement.class, new CharmTransformer()));
  }

  public static IExtensionElement createTreePart(String treeId, IExtensionElement... charms) throws ExtensionException {
    return createExtensionElement(new MockName("treepart"), //$NON-NLS-1$
        new MockStringAttribute("treeReference", treeId), //$NON-NLS-1$
        new MockChildren(charms));
  }

  public static IExtensionElement createCharm(String charmId, String... prerequisites) throws ExtensionException {
    IExtensionElement[] children = createPrerequisiteElements(prerequisites);
    return createExtensionElement(new MockName("charm"), //$NON-NLS-1$
        new MockStringAttribute("id", charmId), //$NON-NLS-1$
        new MockChildren(children));
  }

  private static IExtensionElement[] createPrerequisiteElements(String... prerequisites) {
    return ArrayUtilities.transform(prerequisites, IExtensionElement.class, new CharmPrerequisiteTransformer());
  }

  public static IExtensionElement createGenericCharms(String characterType, IExtensionElement... charms)
      throws ExtensionException {
    return createExtensionElement(new MockName("genericCharms"), //$NON-NLS-1$
        new MockStringAttribute("characterType", characterType), //$NON-NLS-1$
        new MockChildren(charms));
  }
}