package net.sf.anathema.character.attributes;

import java.io.IOException;

import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.basics.repository.input.IFileItemEditorInput;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.trait.DisplayTrait;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;

public class AttributesEditorInput extends FileEditorInput implements IFileItemEditorInput<IAttributes> {

  private final IAttributes attributes;
  private final IDisplayNameProvider displayNameProvider;
  private final AttributesPersister attributesPersister = new AttributesPersister();
  private final IAttributeCharacterContext context;

  public AttributesEditorInput(
      IFile file,
      ImageDescriptor imageDescriptor,
      IDisplayNameProvider displayNameProvider,
      IAttributes attributes,
      IAttributeCharacterContext context) {
    super(file, imageDescriptor);
    this.displayNameProvider = displayNameProvider;
    this.context = context;
    this.attributes = attributes;
  }

  @Override
  public IAttributes getItem() {
    return attributes;
  }

  @Override
  public IAttributes save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    new ItemFileWriter().saveToFile(getFile(), attributesPersister, attributes, monitor);
    return attributes;
  }

  @Override
  public String getName() {
    return displayNameProvider.getDisplayName();
  }

  /** Creates attribute display groups and displaytraits. Displaytraits must be disposed of by clients. */
  public ITraitGroup[] createDisplayGroups() {
    TraitGroup[] groups = context.getTraitGroups();
    for (TraitGroup group : groups) {
      for (String traitId : group.getTraitIds()) {
        group.addTrait(new DisplayTrait(getItem().getTrait(traitId), context.getBasics(), context.getRules()));
      }
    }
    return groups;
  }

  public IFolder getCharacterFolder() {
    return (IFolder) getFile().getParent();
  }
}