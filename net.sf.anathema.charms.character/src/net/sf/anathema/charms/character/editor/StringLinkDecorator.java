package net.sf.anathema.charms.character.editor;

import net.disy.commons.core.provider.IProvider;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.basics.repository.linkage.util.ILink;

public class StringLinkDecorator implements ILink {

  private final ILink original;
  private final IProvider<String> treeIdProvider;

  public StringLinkDecorator(ILink original, IProvider<String> treeIdProvider) {
    this.original = original;
    this.treeIdProvider = treeIdProvider;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof StringLinkDecorator)) {
      return false;
    }
    StringLinkDecorator other = (StringLinkDecorator) obj;
    return ObjectUtilities.equals(original, other.original)
        && ObjectUtilities.equals(treeIdProvider.getObject(), other.treeIdProvider.getObject());
  }

  @Override
  public int hashCode() {
    return original.hashCode();
  }
}