package net.sf.anathema.character.core.type;

import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.jface.resource.ImageDescriptor;

public interface ICharacterType extends IIdentificate {

  public ImageDescriptor getImageDescriptor();

  public String getTraitImageId();
}