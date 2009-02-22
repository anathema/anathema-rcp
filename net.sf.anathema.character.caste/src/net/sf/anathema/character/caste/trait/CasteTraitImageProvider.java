package net.sf.anathema.character.caste.trait;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
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

public class CasteTraitImageProvider extends UnconfiguredExecutableExtension implements ITraitStatusImageProvider {

  @Override
  public Image getImage(IDisplayTrait trait, ICharacterId characterId) {
    ICaste caste = getCaste(characterId);
    return ICharacterCorePluginConstants.IMAGE_REGISTRY.get(caste.getId());
  }

  @Override
  public boolean hasImage(IDisplayTrait trait, ICharacterId characterId) {
    ITraitStatus status = trait.getFavorization().getStatus();
    if (!(status instanceof CasteStatus)) {
      return false;
    }
    ICaste caste = getCaste(characterId);
    return isCasteTrait(caste, trait);
  }

  private ICaste getCaste(ICharacterId characterId) {
    IModelCollection modelCollection = ModelCache.getInstance();
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, ICasteModel.ID);
    ICasteModel model = (ICasteModel) modelCollection.getModel(modelIdentifier);
    ICaste caste = model.getCaste();
    return caste;
  }

  private boolean isCasteTrait(ICaste caste, IDisplayTrait trait) {
    return caste.supportsTrait(trait.getTraitType());
  }
}