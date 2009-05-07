package net.sf.anathema.character.freebies.coverage;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.core.traitview.SurplusPainter;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorDecoration;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.resources.ITraitResources;
import net.sf.anathema.lib.ui.IIntValueView;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.swt.graphics.Image;

public abstract class AbstractSurplusMarkingEditorDecoration<G> extends UnconfiguredExecutableExtension implements
    ITraitGroupEditorDecoration {

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
    viewsByType.put(trait.getTraitType(), view);
    trait.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        update();
      }
    });
    update();
  }

  protected abstract ITraitCollectionContext createContext(
      ITraitGroupEditorInput editorInput,
      IModelCollection modelCollection);

  protected final ITraitGroupEditorInput getInput() {
    return input;
  }

  protected final ITraitCollectionContext getContext() {
    return context;
  }

  protected abstract int getCreationPointsCoveredByCredit(IIdentificate traitType);

  public void update() {
    boolean showSurplusMarking = ToggleSurplusMarkingHandler.isMarkingActive();
    if (showSurplusMarking) {
      calculateCoverageThresholds();
    }
    setShowSurplusMarking();
  }

  private void setShowSurplusMarking() {
    for (IIntValueView display : viewsByType.values()) {
      surplusPainters.get(display).setShowSurplus(ToggleSurplusMarkingHandler.isMarkingActive());
    }
  }

  private void calculateCoverageThresholds() {
    IExperience experience = getContext().getExperience();
    for (Entry<IIdentificate, IIntValueView> entry : viewsByType.entrySet()) {
      IIdentificate traitId = entry.getKey();
      IBasicTrait trait = getContext().getCollection().getTrait(traitId.getId());
      int coveredPoints = experience.isExperienced()
          ? trait.getCreationModel().getValue()
          : getCreationPointsCoveredByCredit(traitId);
      surplusPainters.get(entry.getValue()).setSurplusThreshold(coveredPoints);
    }
  }
}