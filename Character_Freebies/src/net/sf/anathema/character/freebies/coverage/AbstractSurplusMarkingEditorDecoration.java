package net.sf.anathema.character.freebies.coverage;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.core.traitview.SurplusPainter;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorDecoration;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.resources.ITraitResources;
import net.sf.anathema.lib.ui.IIntValueView;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.swt.graphics.Image;

public abstract class AbstractSurplusMarkingEditorDecoration<G> extends AbstractExecutableExtension implements
    ITraitGroupEditorDecoration {

  private boolean mark;
  private final Map<IIntValueView, SurplusPainter> surplusPainters = new HashMap<IIntValueView, SurplusPainter>();
  private final Map<IIdentificate, IIntValueView> viewsByType = new HashMap<IIdentificate, IIntValueView>();
  private ITraitGroupEditorInput input;
  private ITraitCollectionContext context;

  public final void decorate(
      final IInteractiveTrait trait,
      final IExtendableIntValueView view,
      final ITraitGroupEditorInput editorInput) {
    this.input = editorInput;
    this.context = createContext(editorInput, ModelCache.getInstance());
    if (context == null) {
      return;
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
  }

  protected abstract ITraitCollectionContext createContext(
      ITraitGroupEditorInput editorInput,
      IModelCollection modelCollection);

  private void markBonusPoints(boolean enabled) {
    this.mark = enabled;
    calculateCoverage();
    for (IIntValueView display : viewsByType.values()) {
      surplusPainters.get(display).setShowSurplus(mark);
    }
  }

  protected final ITraitGroupEditorInput getInput() {
    return input;
  }

  protected final ITraitCollectionContext getContext() {
    return context;
  }

  private void calculateCoverage() {
    if (mark) {
      for (Entry<IIdentificate, IIntValueView> entry : viewsByType.entrySet()) {
        int coveredPoints = getPointsCoveredByCredit(entry.getKey());
        surplusPainters.get(entry.getValue()).setSurplusThreshold(coveredPoints);
      }
    }
  }

  protected abstract int getPointsCoveredByCredit(IIdentificate traitType);

  public void toggleMarkBonusPoints() {
    markBonusPoints(!mark);
  }
}