package net.sf.anathema.character.freebies.abilities.coverage;

import net.sf.anathema.character.abilities.template.AbilitiesTemplateProvider;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.Character;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.coverage.AbstractSurplusMarkingEditorDecoration;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.lib.util.IIdentificate;

public class SurplusMarkingEditorDecoration<G> extends AbstractSurplusMarkingEditorDecoration<G> {

  @Override
  protected ITraitCollectionContext createContext(ITraitGroupEditorInput editorInput, IModelCollection modelCollection) {
    ICharacterId characterId = editorInput.getCharacterId();
    if (!editorInput.getModelId().equals(IAbilitiesPluginConstants.MODEL_ID)) {
      return null;
    }
    ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(characterId);
    ITraitCollectionTemplate traitTemplate = new AbilitiesTemplateProvider().getTraitTemplate(template.getId());
    return new TraitCollectionContext(
        Character.From(characterId, modelCollection),
        IAbilitiesPluginConstants.MODEL_ID,
        traitTemplate.getGroupTemplate());
  }

  @Override
  public int getCreationPointsCoveredByCredit(IIdentificate traitType) {
    ITraitCollectionContext traitContext = getContext();
    ITraitGroupEditorInput editorInput = getInput();
    ITraitCollectionModel collection = traitContext.getCollection();
    CoverageCalculator calculator = new CoverageCalculator(new CreditManager(), ModelCache.getInstance());
    return calculator.calculate(collection, editorInput.getCharacterId(), traitType);
  }
}