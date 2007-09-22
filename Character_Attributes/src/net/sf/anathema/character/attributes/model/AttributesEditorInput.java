package net.sf.anathema.character.attributes.model;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.attributes.points.AttributePointCalculator;
import net.sf.anathema.character.attributes.points.Dots;
import net.sf.anathema.character.attributes.points.PrimaryAttributeFreebies;
import net.sf.anathema.character.attributes.points.SecondaryAttributeFreebies;
import net.sf.anathema.character.attributes.points.TertiaryAttributeFreebies;
import net.sf.anathema.character.attributes.points.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.attributes.points.coverage.AttributeGroupPriorityCalculator;
import net.sf.anathema.character.attributes.points.coverage.PointCoverageCalculator;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitGroupToDisplayTraitGroupTransformer;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.groupeditor.ISurplusIntViewImageProvider;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;

public class AttributesEditorInput extends AbstractCharacterModelEditorInput<ITraitCollectionModel> implements
    ITraitGroupEditorInput {

  private static final String UNSPENT_FREEBIES_MARKER = "net.sf.anathema.markers.unspent.attribute.freebies"; //$NON-NLS-1$
  private final IDisplayNameProvider displayNameProvider;
  private final AttributesPersister attributesPersister = new AttributesPersister();
  private final ITraitCollectionContext context;
  private final Map<PriorityGroup, Integer> creditByPriority = new HashMap<PriorityGroup, Integer>();

  public AttributesEditorInput(
      final IFile file,
      ImageDescriptor imageDescriptor,
      IDisplayNameProvider displayNameProvider,
      final ITraitCollectionContext context) throws CoreException {
    super(file, imageDescriptor);
    this.displayNameProvider = displayNameProvider;
    this.context = context;
    for (PriorityGroup group : PriorityGroup.values()) {
      String creditId = determineCreditId(group);
      int credit = new CreditManager().getCredit(getModelIdentifier().getCharacterId(), creditId);
      creditByPriority.put(group, credit);
    }
    ModelCache.getInstance().getModel(getModelIdentifier()).addChangeListener(new IChangeListener() {

      @Override
      public void stateChanged() {
        markFile();
      }
    });
    markFile();
  }

  private void markFile() {
    IFile file = getFile();
    if (!file.exists() ) {
      return;
    }
    PriorityGroup priority = PriorityGroup.Primary;
    Dots dots = new AttributePointCalculator(context.getCollection(), context.getTraitGroups()).dotsFor(priority);
    boolean warning = creditByPriority.get(priority) > dots.spentTotally();
    try {
      if (warning) {
        IMarker[] markers = file.findMarkers(UNSPENT_FREEBIES_MARKER, true, IResource.DEPTH_ZERO);
        if (markers.length == 0) {
          file.createMarker(UNSPENT_FREEBIES_MARKER);
        }
      }
      else {
        file.deleteMarkers(UNSPENT_FREEBIES_MARKER, true, IResource.DEPTH_ZERO);
      }
    }
    catch (CoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
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

  @Override
  public ITraitCollectionModel getItem() {
    return context.getCollection();
  }

  @Override
  public ITraitCollectionModel save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    new ItemFileWriter().saveToFile(getFile(), attributesPersister, getItem(), monitor);
    return getItem();
  }

  @Override
  public String getName() {
    return displayNameProvider.getDisplayName();
  }

  /** Creates attribute display groups and displaytraits. Displaytraits must be disposed of by clients. */
  public IDisplayTraitGroup[] createDisplayGroups() {
    return ArrayUtilities.transform(
        context.getTraitGroups(),
        IDisplayTraitGroup.class,
        new TraitGroupToDisplayTraitGroupTransformer(context));
  }

  public IFolder getCharacterFolder() {
    return (IFolder) getFile().getParent();
  }

  @Override
  protected IModelIdentifier getModelIdentifier() {
    return new ModelIdentifier(getCharacterFolder(), Attributes.MODEL_ID);
  }

  @Override
  public String getGroupLabel(IDisplayTraitGroup group) {
    return AttributeMessages.get(group.getId());
  }

  @Override
  public String getTraitLabel(IIdentificate traitType) {
    return AttributeMessages.get(traitType.getId());
  }

  @Override
  public ISurplusIntViewImageProvider getImageProvider() {
    return new SurplusIntViewImageProvider(context.getActiveImageId());
  }

  @Override
  public int getPointsCoveredByCredit(IIdentificate traitType) {
    ITraitGroup traitGroup = findTraitGroup(traitType);
    PriorityGroup priority = new AttributeGroupPriorityCalculator(context).getPriority(traitGroup);
    int credit = creditByPriority.get(priority);
    PointCoverageCalculator calculator = new PointCoverageCalculator(context, credit);
    return calculator.calculateCoverageFor(traitGroup).getPointsCovered(traitType);
  }

  private ITraitGroup findTraitGroup(IIdentificate traitType) {
    for (ITraitGroup group : context.getTraitGroups()) {
      if (ArrayUtilities.contains(group.getTraitIds(), traitType.getId())) {
        return group;
      }
    }
    Object[] arguments = new Object[] { traitType.getId() };
    throw new IllegalArgumentException(MessageFormat.format(
        Messages.AttributesEditorInput_GroupLessTraitMessage,
        arguments));
  }
}