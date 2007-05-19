package net.sf.anathema.basics.repository.persistence;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;

public class RepositoryLocation implements IItemRepositoryLocation {

  private String id;

  public String getIdProposal(IItem< ? > item) {
    return AnathemaStringUtilities.getFileNameRepresentation(item.getPrintName());
  }

  public synchronized void setId(String id) {
    Ensure.ensureNull("Item's id must not be changed.", this.id); //$NON-NLS-1$
    this.id = id;
  }

  public synchronized String getId() {
    return id;
  }
}