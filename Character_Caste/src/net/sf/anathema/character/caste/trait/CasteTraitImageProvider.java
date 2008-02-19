package net.sf.anathema.character.caste.trait;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.status.ITraitStatus;
import net.sf.anathema.character.trait.status.ITraitStatusImageProvider;

import org.eclipse.swt.graphics.Image;

public class CasteTraitImageProvider extends AbstractExecutableExtension implements ITraitStatusImageProvider {

  @Override
  public Image getImage(IDisplayTrait trait, ICharacterId characterId) {
    ITraitStatus status = trait.getFavorization().getStatusModel().getStatus();
    if (!(status instanceof CasteStatus)) {
      return null;
    }
    IModelCollection modelCollection = ModelCache.getInstance();
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, ICasteModel.ID);
    ICasteModel model = (ICasteModel) modelCollection.getModel(modelIdentifier);
    ICaste caste = model.getCaste();
    if (caste.supportsTrait(trait.getTraitType())) {
      return ICharacterCorePluginConstants.IMAGE_REGISTRY.get(caste.getId());
    }
    return null;
  }
}