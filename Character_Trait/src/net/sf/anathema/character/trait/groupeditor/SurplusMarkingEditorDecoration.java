package net.sf.anathema.character.trait.groupeditor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.core.traitview.SurplusPainter;
import net.sf.anathema.character.trait.IDisplayTrait;
import net.sf.anathema.character.trait.resources.ITraitResources;
import net.sf.anathema.lib.ui.IIntValueView;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.swt.graphics.Image;

public class SurplusMarkingEditorDecoration implements ITraitGroupEditorDecoration {
  
  private boolean mark;
  private Map<IIntValueView, SurplusPainter> surplusPainters = new HashMap<IIntValueView, SurplusPainter>();
  private final Map<IIdentificate, IIntValueView> viewsByType = new HashMap<IIdentificate, IIntValueView>();
  private ITraitGroupEditorInput input;


  public void decorate(final IDisplayTrait trait, final IExtendableIntValueView view, ITraitGroupEditorInput editorInput) {
    this.input = editorInput;
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
        int coveredPoints = input.getPointsCoveredByCredit(entry.getKey());
        surplusPainters.get(entry.getValue()).setSurplusThreshold(coveredPoints);
      }
    }
  }
}