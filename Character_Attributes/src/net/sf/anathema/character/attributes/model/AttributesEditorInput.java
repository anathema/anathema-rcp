package net.sf.anathema.character.attributes.model;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.groupeditor.IIntViewImageProvider;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.interactive.InteractiveTraitGroupTransformer;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.preference.TraitPreferenceFactory;
import net.sf.anathema.lib.collection.CollectionUtilities;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class AttributesEditorInput extends AbstractCharacterModelEditorInput<ITraitCollectionModel> implements
    ITraitGroupEditorInput {

  private final AttributesPersister attributesPersister = new AttributesPersister();
  private final ITraitCollectionContext context;
  private final IFavorizationHandler favorizationHandler;

  public AttributesEditorInput(
      final IFile file,
      URL imageUrl,
      IDisplayNameProvider displayNameProvider,
      final ITraitCollectionContext context,
      final IFavorizationHandler favorizationHandler) {
    super(file, imageUrl, displayNameProvider);
    this.context = context;
    this.favorizationHandler = favorizationHandler;
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

  /** Creates attribute display groups and displaytraits. Displaytraits must be disposed of by clients. */
  public List<IDisplayTraitGroup<IInteractiveTrait>> createDisplayGroups() {
    ITraitPreferences preferences = TraitPreferenceFactory.create();
    return CollectionUtilities.transform(context.getTraitGroups(), new InteractiveTraitGroupTransformer(
        context,
        favorizationHandler,
        preferences));
  }

  public IFolder getCharacterFolder() {
    return (IFolder) getFile().getParent();
  }

  @Override
  protected IModelIdentifier getModelIdentifier() {
    return new ModelIdentifier(getCharacterFolder(), Attributes.MODEL_ID);
  }

  @Override
  public String getGroupLabel(IDisplayTraitGroup< ? > group) {
    return AttributeMessages.get(group.getId());
  }

  @Override
  public String getTraitLabel(IIdentificate traitType) {
    return AttributeMessages.get(traitType.getId());
  }

  @Override
  public IIntViewImageProvider getImageProvider() {
    return new IntViewImageProvider(context.getActiveImageId());
  }

  public ITraitGroup findTraitGroup(IIdentificate traitType) {
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

  @Override
  public ICharacterId getCharacterId() {
    return getModelIdentifier().getCharacterId();
  }
}