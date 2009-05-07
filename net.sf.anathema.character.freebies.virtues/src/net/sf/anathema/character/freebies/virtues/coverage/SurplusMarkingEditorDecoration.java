package net.sf.anathema.character.freebies.virtues.coverage;

import net.sf.anathema.character.core.character.Character;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.freebies.coverage.AbstractSurplusMarkingEditorDecoration;
import net.sf.anathema.character.spiritualtraits.model.SpiritualTraitGroupTemplate;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.points.EssenceBonuspointHandler;
import net.sf.anathema.character.spiritualtraits.virtues.VirtueSumCalculator;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.character.trait.points.PointwiseCostDto;
import net.sf.anathema.lib.util.IIdentificate;

public class SurplusMarkingEditorDecoration<G> extends AbstractSurplusMarkingEditorDecoration<G> {

  @Override
  protected ITraitCollectionContext createContext(ITraitGroupEditorInput editorInput, IModelCollection modelCollection) {
    ICharacterId characterId = editorInput.getCharacterId();
    if (!editorInput.getModelId().equals(IPluginConstants.MODEL_ID)) {
      return null;
    }
    return new TraitCollectionContext(
        Character.From(characterId, modelCollection),
        IPluginConstants.MODEL_ID,
        new SpiritualTraitGroupTemplate());
  }

  @Override
  public int getCreationPointsCoveredByCredit(IIdentificate traitType) {
    ITraitCollectionContext traitContext = getContext();
    ITraitGroupEditorInput editorInput = getInput();
    ITraitCollectionModel collection = traitContext.getCollection();
    String traitId = traitType.getId();
    IBasicTrait trait = collection.getTrait(traitId);
    if (IPluginConstants.ESSENCE_ID.equals(traitId)) {
      ICharacterId characterId = editorInput.getCharacterId();
      PointwiseCostDto costDto = EssenceBonuspointHandler.getBonusPointCostDto(characterId);
      return costDto.startValue;
    }
    if (IPluginConstants.WILLPOWER_ID.equals(traitId)) {
      VirtueSumCalculator sumCalculator = new VirtueSumCalculator(collection);
      return sumCalculator.getSumOfTwoHighestVirtues();
    }
    return trait.getCreationModel().getValue();
  }
}