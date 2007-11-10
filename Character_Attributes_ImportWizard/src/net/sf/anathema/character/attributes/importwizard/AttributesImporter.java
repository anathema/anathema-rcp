package net.sf.anathema.character.attributes.importwizard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.character.attributes.AttributesPlugin;
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
      IFile targetFile = container.getFile(new Path("attributes.model"));
      new FileWriter().saveToFile(targetFile, outputStream, new NullProgressMonitor());
      return Status.OK_STATUS;
    }
    catch (TransformerException e) {
      throw new CoreException(AttributesPlugin.getDefaultInstance().createErrorStatus(
          "Could not convert attributes.",
          e));
    }
  }
}