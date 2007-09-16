package net.sf.anathema.character.attributes.model;

import java.io.IOException;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.attributes.points.AttributeGroupPriorityCalculator;
import net.sf.anathema.character.attributes.points.AttributePointCalculator;
import net.sf.anathema.character.attributes.points.IAttributeGroupFreebiesHandler;
import net.sf.anathema.character.attributes.points.PointCoverageCalculator;
import net.sf.anathema.character.attributes.points.PrimaryAttributeFreebies;
import net.sf.anathema.character.attributes.points.SecondaryAttributeFreebies;
import net.sf.anathema.character.attributes.points.TertiaryAttributeFreebies;
import net.sf.anathema.character.attributes.points.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.character.core.model.IModelIdentifier;
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;

public class AttributesEditorInput extends AbstractCharacterModelEditorInput<ITraitCollectionModel> implements
    ITraitGroupEditorInput {

  private final IDisplayNameProvider displayNameProvider;
  private final AttributesPersister attributesPersister = new AttributesPersister();
  private final ITraitCollectionContext context;

  public AttributesEditorInput(
      IFile file,
      ImageDescriptor imageDescriptor,
      IDisplayNameProvider displayNameProvider,
      ITraitCollectionContext context) {
    super(file, imageDescriptor);
    this.displayNameProvider = displayNameProvider;
    this.context = context;
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
  public int getPointsCoveredByCredit(IIdentificate traitType) {
    AttributeGroupPriorityCalculator priorityCalculator = new AttributeGroupPriorityCalculator(context);
    ITraitGroup traitGroup = null;
    for (ITraitGroup group : context.getTraitGroups()) {
      if (ArrayUtilities.contains(group.getTraitIds(), traitType.getId())) {
        traitGroup = group;
        break;
      }
    }
    PriorityGroup priority = priorityCalculator.getPriority(traitGroup);
    IAttributeGroupFreebiesHandler freebies = null;
    if (priority == AttributePointCalculator.PRIMARY) {
      freebies = new PrimaryAttributeFreebies();
    }
    if (priority == AttributePointCalculator.SECONDARY) {
      freebies = new SecondaryAttributeFreebies();
    }
    if (priority == AttributePointCalculator.TERTIARY) {
      freebies = new TertiaryAttributeFreebies();
    }
    int credit = new CreditManager().getCredit(getModelIdentifier().getCharacterId(), freebies.getCreditId());
    PointCoverageCalculator calculator = new PointCoverageCalculator(context, credit);
    calculator.calculateFor(traitGroup);
    return calculator.pointCoverage(traitType);
  }

  @Override
  public ISurplusIntViewImageProvider getImageProvider() {
    return new SurplusIntViewImageProvider();
  }
}