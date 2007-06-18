package net.sf.anathema.character.attributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.basics.repository.input.IFileItemEditorInput;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.basics.ICharacterBasics;
import net.sf.anathema.character.trait.DisplayTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IDisplayTrait;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;

public class AttributesEditorInput extends FileEditorInput implements IFileItemEditorInput<IAttributes> {

  private IAttributes attributes;
  private final IDisplayNameProvider displayNameProvider;
  private final AttributesPersister attributesPersister = new AttributesPersister();

  public AttributesEditorInput(IFile file, ImageDescriptor imageDescriptor, IDisplayNameProvider displayNameProvider)
      throws PersistenceException,
      CoreException {
    super(file, imageDescriptor);
    this.displayNameProvider = displayNameProvider;
    this.attributes = attributesPersister.load(DocumentUtilities.read(file.getContents()));
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
    // TODO Auf Änderungen des Namens reagieren
    return "Attributes - " + displayNameProvider.getDisplayName();
  }

  public Iterable<IDisplayTrait> createDisplayTraits() {
    // TODO Daten in den ViewElements lagern (Basics im Parent) ??? 
    ICharacterBasics basics = new ICharacterBasics() {
      @Override
      public boolean isExperienced() {
        return false;
      }
    };
    // TODO Reihenfolge und Darstellungsgruppen 
    List<IDisplayTrait> displayTraits = new ArrayList<IDisplayTrait>();
    for (IBasicTrait trait : getItem().getTraits()) {
      displayTraits.add(new DisplayTrait(trait, basics));
    }
    return displayTraits;
  }
}