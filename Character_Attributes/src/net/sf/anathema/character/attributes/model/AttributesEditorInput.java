package net.sf.anathema.character.attributes.model;

import java.net.URL;
import java.text.MessageFormat;
import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
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
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

public class AttributesEditorInput extends AbstractCharacterModelEditorInput<ITraitCollectionModel> implements
    ITraitGroupEditorInput {

  private final ITraitCollectionContext context;
  private final IFavorizationHandler favorizationHandler;

  public AttributesEditorInput(
      final IFile file,
      URL imageUrl,
      IDisplayNameProvider displayNameProvider,
      final ITraitCollectionContext context,
      final IFavorizationHandler favorizationHandler) {
    super(file, imageUrl, displayNameProvider, new AttributesPersister());
    this.context = context;
    this.favorizationHandler = favorizationHandler;
  }

  @Override
  public ITraitCollectionModel getItem() {
    return context.getCollection();
  }

  /** Creates attribute display groups and displaytraits. Displaytraits must be disposed of by clients. */
  public List<IDisplayTraitGroup<IInteractiveTrait>> createDisplayGroups() {
    ITraitPreferences preferences = TraitPreferenceFactory.create();
    return CollectionUtilities.transform(context.getTraitGroups(), new InteractiveTraitGroupTransformer(
        context,
        favorizationHandler,
        preferences));
  }

  @Override
  public IFolder getCharacterFolder() {
    return super.getCharacterFolder();
  }
  
  @Override
  protected String getModelId() {
    return Attributes.MODEL_ID;
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