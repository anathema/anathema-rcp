package net.sf.anathema.character.freebies.attributes.mark;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.attributes.model.AttributesContext;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.mark.IMarking;
import net.sf.anathema.character.freebies.attributes.PrimaryAttributeFreebies;
import net.sf.anathema.character.freebies.attributes.SecondaryAttributeFreebies;
import net.sf.anathema.character.freebies.attributes.TertiaryAttributeFreebies;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.Dots;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class AttributeMarking implements IDisposable, IMarking {
  private static final String UNSPENT_FREEBIES_MARKER = "net.sf.anathema.markers.unspent.attribute.freebies"; //$NON-NLS-1$
  private final Map<PriorityGroup, Integer> creditByPriority = new HashMap<PriorityGroup, Integer>();
  private final IModelIdentifier modelIdentifier;
  private final ITraitCollectionContext context;
  private final IModelCollection modelProvider;
  private final IFile modelResource;
  private final IChangeListener markListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      markFile();
    }
  };

  public AttributeMarking(
      ICreditManager creditManager,
      IModelCollection modelProvider,
      IModelIdentifier modelIdentifier,
      IFile modelResource) {
    this.modelProvider = modelProvider;
    this.modelResource = modelResource;
    this.context = AttributesContext.create(modelIdentifier.getCharacterId(), modelProvider);
    this.modelIdentifier = modelIdentifier;
    for (PriorityGroup group : PriorityGroup.values()) {
      String creditId = determineCreditId(group);
      int credit = creditManager.getCredit(modelIdentifier.getCharacterId(), creditId);
      creditByPriority.put(group, credit);
    }
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

  public void mark() {
    getModel().addChangeListener(markListener);
    markFile();
  }

  private IModel getModel() {
    return modelProvider.getModel(modelIdentifier);
  }

  private void markFile() {
    if (!modelResource.exists()) {
      return;
    }
    PriorityGroup priority = PriorityGroup.Primary;
    Dots dots = new AttributePointCalculator(context.getCollection(), context.getTraitGroups()).dotsFor(priority);
    boolean warning = creditByPriority.get(priority) > dots.spentTotally();
    try {
      if (warning) {
        IMarker[] markers = modelResource.findMarkers(UNSPENT_FREEBIES_MARKER, true, IResource.DEPTH_ZERO);
        if (markers.length == 0) {
          modelResource.createMarker(UNSPENT_FREEBIES_MARKER);
        }
      }
      else {
        modelResource.deleteMarkers(UNSPENT_FREEBIES_MARKER, true, IResource.DEPTH_ZERO);
      }
    }
    catch (CoreException e) {
    }
  }

  @Override
  public void dispose() {
    IModel model = getModel();
    if (model != null) {
      model.removeChangeListener(markListener);
    }
  }
}