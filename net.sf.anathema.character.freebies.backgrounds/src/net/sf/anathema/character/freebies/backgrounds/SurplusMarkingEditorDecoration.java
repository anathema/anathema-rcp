package net.sf.anathema.character.freebies.backgrounds;

import net.sf.anathema.character.backgrounds.model.IBackgroundModel;
import net.sf.anathema.character.core.character.Character;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.freebies.coverage.AbstractSurplusMarkingEditorDecoration;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.lib.util.IIdentificate;

public class SurplusMarkingEditorDecoration<G> extends AbstractSurplusMarkingEditorDecoration<G> {

  private final class NullTraitGroupTemplate implements ITraitGroupTemplate {
    @Override
    public TraitGroup[] getGroups() {
      return new TraitGroup[0];
    }
  }

  @Override
  protected ITraitCollectionContext createContext(ITraitGroupEditorInput editorInput, IModelCollection modelCollection) {
    ICharacterId characterId = editorInput.getCharacterId();
    if (!editorInput.getModelId().equals(IBackgroundModel.MODEL_ID)) {
      return null;
    }
    return new TraitCollectionContext(
        Character.From(characterId, modelCollection),
        IBackgroundModel.MODEL_ID,
        new NullTraitGroupTemplate());
  }

  @Override
  public int getCreationPointsCoveredByCredit(IIdentificate traitType) {
    ITraitCollectionContext traitContext = getContext();
    ITraitCollectionModel collection = traitContext.getCollection();
    IBasicTrait trait = collection.getTrait(traitType.getId());
    return trait.getCreationModel().getValue();
  }
}