package net.sf.anathema.character.caste.sheet;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.caste.model.ICaste;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.report.text.ICharacterText;

public class CasteCharacterText extends AbstractExecutableExtension implements ICharacterText {

  @Override
  public String getLabel() {
    return "Caste";
  }

  @Override
  public String getTextFor(ICharacter character) {
    ICaste caste = getCasteModel(character).getCaste();
    if (caste == null) {
      return null;
    }
    return caste.getPrintName();
  }

  @Override
  public boolean isActiveFor(ICharacter character) {
    return getCasteModel(character) != null;
  }

  private ICasteModel getCasteModel(ICharacter character) {
    return (ICasteModel) character.getModel(ICasteModel.ID);
  }
}