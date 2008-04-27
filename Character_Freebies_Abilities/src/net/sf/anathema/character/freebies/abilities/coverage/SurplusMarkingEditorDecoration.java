package net.sf.anathema.character.freebies.abilities.coverage;

import net.sf.anathema.character.abilities.template.AbilitiesTemplateProvider;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.freebies.coverage.AbstractSurplusMarkingEditorDecoration;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorDecoration;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;
import net.sf.anathema.lib.util.IIdentificate;

public class SurplusMarkingEditorDecoration<G> extends AbstractSurplusMarkingEditorDecoration<G> implements
    ITraitGroupEditorDecoration {

  @Override
  protected ITraitCollectionContext createContext(ITraitGroupEditorInput editorInput, IModelCollection modelCollection) {
    ICharacterId characterId = editorInput.getCharacterId();
    if (!editorInput.getModelId().equals(IAbilitiesPluginConstants.MODEL_ID)) {
      return null;
    }
    ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(characterId);
    ITraitCollectionTemplate traitTemplate = new AbilitiesTemplateProvider().getTraitTemplate(template.getId());
    return TraitCollectionContext.create(
        characterId,
        modelCollection,
        IAbilitiesPluginConstants.MODEL_ID,
        traitTemplate,
        traitTemplate);
  }

  @Override
  protected int getPointsCoveredByCredit(IIdentificate traitType) {
    // TODO: Case 191: Komplette Behandlung für Bonuspunktausgaben
    ITraitCollectionContext traitContext = getContext();
    IBasicTrait trait = traitContext.getCollection().getTrait(traitType.getId());
    return Math.min(trait.getCreationModel().getValue(), 3);
  }
}