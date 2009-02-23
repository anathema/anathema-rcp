package net.sf.anathema.character.caste.sheet;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.report.text.ICharacterText;

public class CasteCharacterText extends UnconfiguredExecutableExtension implements ICharacterText {

  @Override
  public String getLabel() {
    return Messages.CasteCharacterText_Label;
  }

  @Override
  public String getTextFor(ICharacter character) {
    ICaste caste = getCasteModel(character).getCaste();
    if (caste == null) {
      return ""; //$NON-NLS-1$
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