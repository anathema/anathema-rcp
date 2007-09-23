package net.sf.anathema.character.freebies.attributes.coverage;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.AttributesContext;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.core.traitview.SurplusPainter;
import net.sf.anathema.character.freebies.attributes.PrimaryAttributeFreebies;
import net.sf.anathema.character.freebies.attributes.SecondaryAttributeFreebies;
import net.sf.anathema.character.freebies.attributes.TertiaryAttributeFreebies;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.IDisplayTrait;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorDecoration;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.character.trait.resources.ITraitResources;
import net.sf.anathema.lib.ui.IIntValueView;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.swt.graphics.Image;

public class SurplusMarkingEditorDecoration<G> extends AbstractExecutableExtension implements ITraitGroupEditorDecoration {

  private boolean mark;
  private Map<IIntValueView, SurplusPainter> surplusPainters = new HashMap<IIntValueView, SurplusPainter>();
  private final Map<IIdentificate, IIntValueView> viewsByType = new HashMap<IIdentificate, IIntValueView>();
  private ITraitGroupEditorInput input;
  private final Map<PriorityGroup, Integer> creditByPriority = new HashMap<PriorityGroup, Integer>();
  private AttributesContext context;

  public void decorate(final IDisplayTrait trait, final IExtendableIntValueView view, ITraitGroupEditorInput editorInput) {
    ICreditManager creditManager = new CreditManager();
    this.input = editorInput;
    this.context = AttributesContext.create(editorInput.getCharacterId(), ModelCache.getInstance());
    for (PriorityGroup group : PriorityGroup.values()) {
      String creditId = determineCreditId(group);
      int credit = creditManager.getCredit(editorInput.getCharacterId(), creditId);
      creditByPriority.put(group, credit);
    }
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
    // TODO Wieder rausnehmen, wenn die Action wiederbelebt wurde
    markBonusPoints(true);
  }

  private String determineCreditId(PriorityGroup priority) {
    switch (priority) {
      case Primary:
        return new PrimaryAttributeFreebies().getCreditId();
      case Secondary:
        return new SecondaryAttributeFreebies().getCreditId();
      case Tertiary:
        return new TertiaryAttributeFreebies().getCreditId();
    }
    throw new UnreachableCodeReachedException();
  }

  public void markBonusPoints(boolean enabled) {
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
    PriorityGroup priority = new AttributeGroupPriorityCalculator(context).getPriority(traitGroup);
    int credit = creditByPriority.get(priority);
    PointCoverageCalculator calculator = new PointCoverageCalculator(context, credit);
    return calculator.calculateCoverageFor(traitGroup).getPointsCovered(traitType);
  }
}