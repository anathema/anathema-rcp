package net.sf.anathema.character.freebies.attributes.coverage;

import java.util.Map;

import net.sf.anathema.character.attributes.model.AttributesContext;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.freebies.attributes.AttributePriorityFreebies;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.Dots;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.coverage.AbstractSurplusMarkingEditorDecoration;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.lib.util.IIdentificate;

public class SurplusMarkingEditorDecoration<G> extends AbstractSurplusMarkingEditorDecoration<G> {

  @Override
  protected ITraitCollectionContext createContext(ITraitGroupEditorInput editorInput, IModelCollection modelCollection) {
    ICharacterId characterId = editorInput.getCharacterId();
    if (!editorInput.getModelId().equals(IAttributesPluginConstants.MODEL_ID)) {
      return null;
    }
    return AttributesContext.create(characterId, modelCollection);
  }

  @Override
  protected int getPointsCoveredByCredit(IIdentificate traitType) {
    ITraitGroupEditorInput editorInput = getInput();
    ITraitCollectionContext traitContext = getContext();
    ITraitGroup traitGroup = editorInput.findTraitGroup(traitType);
    ICharacterId characterId = editorInput.getCharacterId();
    AttributePriorityFreebies priorityFreebies = new AttributePriorityFreebies();
    Map<Priority, Integer> creditByPriority = priorityFreebies.get(characterId, new CreditManager());
    ITraitGroup[] traitGroups = traitContext.getTraitGroups();
    ITraitCollectionModel collection = traitContext.getCollection();
    Dots dots = new AttributePointCalculator(creditByPriority, collection, traitGroups).getDotsFor(traitType);
    PointCoverageCalculator calculator = new PointCoverageCalculator(traitContext, dots.getCredit());
    return calculator.calculateCoverageFor(traitGroup).getPointsCovered(traitType);
  }
}