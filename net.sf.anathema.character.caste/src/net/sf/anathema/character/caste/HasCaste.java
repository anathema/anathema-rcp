package net.sf.anathema.character.caste;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.properties.IProperty;

public class HasCaste extends UnconfiguredExecutableExtension implements IProperty {

  @Override
  public boolean has(ICharacter character, String property) {
    ICasteModel casteModel = (ICasteModel) character.getModel(ICasteModel.ID);
    ICaste caste = casteModel.getCaste();
    return caste == null ? false : caste.getId().equals(property);
  }
}