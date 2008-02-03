package net.sf.anathema.character.freebies.attributes.coverage;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.AttributesContext;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.core.traitview.SurplusPainter;
import net.sf.anathema.character.freebies.attributes.AttributePriorityFreebies;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.Dots;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorDecoration;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.resources.ITraitResources;
import net.sf.anathema.lib.ui.IIntValueView;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.swt.graphics.Image;

public class SurplusMarkingEditorDecoration<G> extends AbstractExecutableExtension implements
    ITraitGroupEditorDecoration {

  private boolean mark;
  private final Map<IIntValueView, SurplusPainter> surplusPainters = new HashMap<IIntValueView, SurplusPainter>();
  private final Map<IIdentificate, IIntValueView> viewsByType = new HashMap<IIdentificate, IIntValueView>();
  private ITraitGroupEditorInput input;
  private AttributesContext context;

  public void decorate(
      final IInteractiveTrait trait,
      final IExtendableIntValueView view,
      ITraitGroupEditorInput editorInput) {
    this.input = editorInput;
    this.context = AttributesContext.create(editorInput.getCharacterId(), ModelCache.getInstance());
    Image surplusImage = ICharacterCorePluginConstants.IMAGE_REGISTRY.get(ITraitResources.SURPLUS_BUTTON);
    SurplusPainter surplusPainter = new SurplusPainter(surplusImage);
    surplusPainters.put(view, surplusPainter);
    view.addPainter(surplusPainter);
    trait.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        calculateCoverage();
      }
    });
    viewsByType.put(trait.getTraitType(), view);
  }

  private void markBonusPoints(boolean enabled) {
    this.mark = enabled;
    calculateCoverage();
    for (IIntValueView display : viewsByType.values()) {
      surplusPainters.get(display).setShowSurplus(mark);
    }
  }

  private void calculateCoverage() {
    if (mark) {
      for (Entry<IIdentificate, IIntValueView> entry : viewsByType.entrySet()) {
        int coveredPoints = getPointsCoveredByCredit(entry.getKey());
        surplusPainters.get(entry.getValue()).setSurplusThreshold(coveredPoints);
      }
    }
  }

  private int getPointsCoveredByCredit(IIdentificate traitType) {
    ITraitGroup traitGroup = input.findTraitGroup(traitType);
    Map<Priority, Integer> creditByPriority = new AttributePriorityFreebies().get(
        input.getCharacterId(),
        new CreditManager());
    TraitGroup[] traitGroups = context.getTraitGroups();
    ITraitCollectionModel collection = context.getCollection();
    Dots dots = new AttributePointCalculator(creditByPriority, collection, traitGroups).getDotsFor(traitType);
    PointCoverageCalculator calculator = new PointCoverageCalculator(context, dots.getCredit());
    return calculator.calculateCoverageFor(traitGroup).getPointsCovered(traitType);
  }

  public void toggleMarkBonusPoints() {
    markBonusPoints(!mark);
  }
}