package net.sf.anathema.character.attributes.importwizard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.character.attributes.AttributesPlugin;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
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

public class AttributesImporter extends AbstractExecutableExtension implements IModelImporter {

  @Override
  public IStatus runImport(IContainer container, Document document) throws IOException, CoreException {
    try {
      Document convertedDocument = AttributesConverter.convertAttributes(document);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      DocumentUtilities.save(convertedDocument, outputStream);
      String filename = new ModelExtensionPoint().getModelFilename(Attributes.MODEL_ID);
      IFile targetFile = container.getFile(new Path(filename));
      new FileWriter().saveToFile(targetFile, outputStream, new NullProgressMonitor());
      return Status.OK_STATUS;
    }
    catch (TransformerException e) {
      throw new CoreException(AttributesPlugin.getDefaultInstance().createErrorStatus(
          Messages.AttributesImporter_ErrorMessage,
          e));
    }
  }
}