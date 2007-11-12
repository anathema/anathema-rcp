package net.sf.anathema.character.description.importwizard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.description.ICharacterDescription;
import net.sf.anathema.character.importwizard.IModelImporter;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

public class DescriptionImporter extends AbstractExecutableExtension implements IModelImporter {

  @Override
  public IStatus runImport(IContainer container, Document document) throws IOException, CoreException {
    Document convertedDocument = new DescriptionConverter().convert(document);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DocumentUtilities.save(convertedDocument, outputStream);
    String filename = new ModelExtensionPoint().getModelFilename(ICharacterDescription.MODEL_ID);
    IFile targetFile = container.getFile(new Path(filename));
    new FileWriter().saveToFile(targetFile, outputStream, new NullProgressMonitor());
    return Status.OK_STATUS;
  }
}