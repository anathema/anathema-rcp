package net.sf.anathema.character.caste.model;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.content.IModelContentCheck;

public class CasteModelCheck extends UnconfiguredExecutableExtension implements IModelContentCheck {

  @Override
  public boolean evaluate(IModelContainer container, String content) {
    ICasteModel model = (ICasteModel) container.getModel(ICasteModel.ID);
    if (model == null) {
      return false;
    }
    ICaste caste = model.getCaste();
    if (caste == null) {
      return false;
    }
    return caste.getId().equals(content.trim());
  }
}