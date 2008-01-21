package net.sf.anathema.character.caste.trait;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.caste.model.ICaste;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.status.ITraitStatusImageProvider;

import org.eclipse.swt.graphics.Image;

public class CasteTraitImageProvider extends AbstractExecutableExtension implements ITraitStatusImageProvider {

  @Override
  public Image getImage(IDisplayTrait trait, ICharacterId characterId) {
    IModelCollection modelCollection = ModelCache.getInstance();
    ICasteModel model = (ICasteModel) modelCollection.getModel(new ModelIdentifier(characterId, ICasteModel.ID));
    if (model == null) {
      return null;
    }    
    ICaste caste = model.getCaste();
    if (caste != null && caste.supportsTrait(trait.getTraitType())) {
      return ICharacterCorePluginConstants.IMAGE_REGISTRY.get(caste.getId());
    }
    return null;
  }
}