package net.sf.anathema.character.importwizard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

public class ModelImporter implements IModelImporter {

  private final IDocumentConverter converter;
  private final String modelId;

  public ModelImporter(IDocumentConverter converter, String modelId) {
    this.converter = converter;
    this.modelId = modelId;
  }

  public IStatus runImport(IContainer container, Document document) throws IOException, CoreException {
    Document convertedDocument = converter.convert(document);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DocumentUtilities.save(convertedDocument, outputStream);
    String filename = new ModelExtensionPoint().getModelFilename(modelId);
    IFile targetFile = container.getFile(new Path(filename));
    new FileWriter().saveToFile(targetFile, outputStream, new NullProgressMonitor());
    return Status.OK_STATUS;
  }
}