package net.sf.anathema.character.core.importwizard;

import java.io.File;
import java.io.FileNotFoundException;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.importwizard.AbstractImportWizard;
import net.sf.anathema.basics.importwizard.FileSelectionStatusFactory;
import net.sf.anathema.basics.importwizard.FileSelectionWizardPage;
import net.sf.anathema.basics.importwizard.IFileSelectionModel;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.IWizardPage;

public class CharacterImportWizard extends AbstractImportWizard {

  public CharacterImportWizard() {
    super(new FileSelectionStatusFactory());
  }

  @Override
  protected IWizardPage createImportPage(IFileSelectionModel filemodel, BooleanModel openmodel) {
    return new FileSelectionWizardPage(filemodel, openmodel, new CharacterImportMessages(), new OpenCharacterDialog());
  }

  @Override
  protected IFile createInternalFile(String filename) throws CoreException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected IItemType getItemType() {
    return CharacterRepositoryUtilities.getCharacterItemType();
  }

  @Override
  protected void runImport(File externalFile, IFile internalFile, IProgressMonitor monitor)
      throws CoreException,
      FileNotFoundException {
    // TODO Auto-generated method stub

  }

  @Override
  protected void undoImport(IFile internalFile, IProgressMonitor monitor) throws CoreException {
    internalFile.getParent().delete(true, monitor);
  }
}