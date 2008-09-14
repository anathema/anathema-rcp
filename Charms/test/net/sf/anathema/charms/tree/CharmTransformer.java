/**
 * 
 */
package net.sf.anathema.charms.tree;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class CharmTransformer implements ITransformer<String, IExtensionElement> {
  @Override
  public IExtensionElement transform(String charmId) {
    try {
      return CharmTreeExtensionPointObjectMother.createCharm(charmId);
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}