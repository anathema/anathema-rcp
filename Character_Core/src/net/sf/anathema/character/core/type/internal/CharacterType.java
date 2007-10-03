package net.sf.anathema.character.core.type.internal;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.ICharacterType;

import org.eclipse.jface.resource.ImageDescriptor;

public class CharacterType implements ICharacterType {

  private final IExtensionElement element;

  public CharacterType(IExtensionElement element) {
    this.element = element;
  }
  
  public ImageDescriptor getImageDescriptor() {
    return element.createImageDescriptorFromAttribute("image"); //$NON-NLS-1$
  }
  
  public String getTraitImageId() {
    return element.getAttribute("traitImageId"); //$NON-NLS-1$
  }

  @Override
  public String getId() {
    return element.getAttribute("id"); //$NON-NLS-1$
  }
  
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ICharacterType)) {
      return false;
    }
    ICharacterType other = (ICharacterType) obj;
    return getId().equals(other.getId());
  }
  
  @Override
  public int hashCode() {
    return getId().hashCode();
  }
}