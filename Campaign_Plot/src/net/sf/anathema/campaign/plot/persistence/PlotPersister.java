package net.sf.anathema.campaign.plot.persistence;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.campaign.plot.repository.PlotPart;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;

public class PlotPersister {

  public static final String HIERARCHY_FILE_NAME = "hierarchy.xml"; //$NON-NLS-1$

  private void addChildren(PlotPart root, Element element) throws PersistenceException {
    for (Element childElement : ElementUtilities.elements(element)) {
      String id = ElementUtilities.getRequiredAttrib(childElement, ISeriesPersistenceConstants.ATTRIB_REPOSITORY_ID);
      PlotPart newPart = root.addChild(id);
      addChildren(newPart, childElement);
    }
  }

  private IPlotPart load(Document document) throws PersistenceException {
    PlotPart root = PlotPart.createPlotRoot();
    Element element = document.getRootElement().element(ISeriesPersistenceConstants.TAG_PLOT);
    addChildren(root, element);
    return root;
  }

  public IPlotPart load(IFolder folder) throws PersistenceException {
    InputStream stream = null;
    try {
      IFile file = folder.getFile(HIERARCHY_FILE_NAME);
      stream = file.getContents();
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(stream);
      return load(document);
    }
    catch (DocumentException e) {
      throw new PersistenceException(e);
    }
    catch (CoreException e) {
      throw new PersistenceException(e);
    }
    finally {
      IOUtilities.close(stream);
    }
  }

  private void save(Element parentElement, IPlotPart part) {
    Element partElement = parentElement.addElement(part.getPlotUnit().getPersistenceString());
    partElement.addAttribute(ISeriesPersistenceConstants.ATTRIB_REPOSITORY_ID, part.getRepositoryId());
    for (IPlotPart childPart : part.getChildren()) {
      save(partElement, childPart);
    }
  }

  public void saveHierarchy(IFolder folder, IPlotPart parentPart) throws IOException, CoreException {
    IPlotPart root = parentPart.getRoot();
    IFile file = folder.getFile(HIERARCHY_FILE_NAME);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      Document document = DocumentHelper.createDocument(DocumentHelper.createElement("hierarchy")); //$NON-NLS-1$
      Element plotElement = document.getRootElement().addElement(ISeriesPersistenceConstants.TAG_PLOT);
      for (IPlotPart plotPart : root.getChildren()) {
        save(plotElement, plotPart);
      }
      DocumentUtilities.save(document, outputStream);
      new FileWriter().saveToFile(file, outputStream);
    }
    finally {
      IOUtilities.close(outputStream);
    }
  }
}