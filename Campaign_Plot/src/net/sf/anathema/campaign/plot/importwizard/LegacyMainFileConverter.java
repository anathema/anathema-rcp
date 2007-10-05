package net.sf.anathema.campaign.plot.importwizard;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

public class LegacyMainFileConverter {
  private static final String HIERARCHY_FILE_NAME = "hierarchy.xml"; //$NON-NLS-1$
  private static final String MAIN_FILE_NAME = "main.srs"; //$NON-NLS-1$

  public void convert(File externalFile, IFile internalFile, IProgressMonitor monitor) throws CoreException {
    try {
      monitor.subTask("Extracting Hierarchy");
      Document externalMainDocument = DocumentUtilities.read(new File(externalFile, MAIN_FILE_NAME));
      monitor.worked(1);
      extractContent(internalFile, monitor, externalMainDocument);
      monitor.worked(1);
      extractHierarchy(internalFile, monitor, externalMainDocument);
      monitor.worked(1);
    }
    catch (PersistenceException e) {
      throw new CoreException(PlotPlugin.getDefaultInstance().createErrorStatus(
          "An error occured while converting the Plot.",
          e));
    }
    catch (IOException e) {
      throw new CoreException(PlotPlugin.getDefaultInstance().createErrorStatus(
          "An error occured while converting the Plot.",
          e));
    }
  }

  private void extractHierarchy(IFile internalFile, IProgressMonitor monitor, Document externalMainDocument)
      throws CoreException,
      PersistenceException,
      IOException {
    Document internalHierarchy = XSLPlotConverter.createHierarchy(externalMainDocument);
    writeDocument(monitor, internalFile.getParent().getFile(new Path(HIERARCHY_FILE_NAME)), internalHierarchy);
  }

  private void extractContent(IFile internalFile, IProgressMonitor monitor, Document externalMainDocument)
      throws IOException,
      CoreException,
      PersistenceException {
    Document internalContent = XSLPlotConverter.createContent(externalMainDocument);
    writeDocument(monitor, internalFile, internalContent);
  }

  private void writeDocument(IProgressMonitor monitor, IFile hierarchyFile, Document internalHierarchy)
      throws IOException,
      CoreException {
    ByteArrayOutputStream hierarchyStream = new ByteArrayOutputStream();
    DocumentUtilities.save(internalHierarchy, hierarchyStream);
    new FileWriter().saveToFile(hierarchyFile, hierarchyStream, monitor);
  }
}