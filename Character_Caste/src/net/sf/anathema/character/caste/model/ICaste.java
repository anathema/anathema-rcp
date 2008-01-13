package net.sf.anathema.character.caste.model;

import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.jface.resource.ImageDescriptor;

public interface ICaste {

  public String getId();

  public String getPrintName();

  public ImageDescriptor getIcon();

  public boolean supportsTrait(IIdentificate traitType);
}