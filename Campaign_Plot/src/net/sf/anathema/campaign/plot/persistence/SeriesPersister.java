package net.sf.anathema.campaign.plot.persistence;

import java.io.InputStream;
import java.util.List;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.item.AnathemaDataItem;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.data.IItemDescription;
import net.sf.anathema.basics.item.data.ItemDescription;
import net.sf.anathema.basics.item.data.TextPersister;
import net.sf.anathema.campaign.plot.item.IPlotElement;
import net.sf.anathema.campaign.plot.item.IPlotModel;
import net.sf.anathema.campaign.plot.item.ISeries;
import net.sf.anathema.campaign.plot.item.Series;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;

public class SeriesPersister implements ISeriesPersistenceConstants {

  private final TextPersister textPersister = new TextPersister();

  protected final void restoreItemDescription(Element documentRoot, IItemDescription description) {
    textPersister.restoreTextualDescription(documentRoot, TAG_NAME, description.getName());
    textPersister.restoreTextualDescription(documentRoot, TAG_SUMMARY, description.getContent());
  }

  public final IItem<ISeries> load(IFolder folder) throws PersistenceException {
    InputStream stream = null;
    try {
      IFile file = folder.getFile("main.srs"); //$NON-NLS-1$
      stream = file.getContents();
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(stream);
      return load(document, folder);
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

  private IItem<ISeries> load(Document xmlDocument, IFolder folder) throws PersistenceException {
    ISeries seriesData = new Series();
    IItem<ISeries> item = new AnathemaDataItem<ISeries>(seriesData);
    Element documentRoot = xmlDocument.getRootElement();
    restoreItemDescription(documentRoot, seriesData.getPlot().getRootElement().getDescription());
    loadPlot(seriesData.getPlot(), xmlDocument.getRootElement(), folder);
    return item;
  }

  private void loadPlot(IPlotModel plot, Element parent, IFolder folder) throws PersistenceException {
    Element plotElement = parent.element(TAG_PLOT);
    if (plotElement == null) {
      return;
    }
    loadPlotElement(plotElement, plot.getRootElement(), folder);
  }

  private void loadPlotElement(Element element, IPlotElement parentElement, IFolder folder) throws PersistenceException {
    List<Element> subXMLElements = ElementUtilities.elements(element);
    for (Object elementObject : subXMLElements) {
      Element plotItemXMLElement = (Element) elementObject;
      String repositoryId = plotItemXMLElement.attributeValue(ATTRIB_REPOSITORY_ID);
      IItemDescription description = loadPlotElementDescription(repositoryId, folder);
      IPlotElement subElement = parentElement.addChild(description, repositoryId);
      loadPlotElement(plotItemXMLElement, subElement, folder);
    }
  }

  public IItemDescription loadPlotElementDescription(String repositoryId, IFolder folder) throws PersistenceException {
    InputStream stream = null;
    try {
      stream = folder.getFile(repositoryId + ".srs").getContents(); //$NON-NLS-1$
      SAXReader saxReader = new SAXReader();
      Element subFileRootElement = saxReader.read(stream).getRootElement();
      IItemDescription description = new ItemDescription();
      restoreItemDescription(subFileRootElement, description);
      return description;
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

  public IItem<ISeries> createNew() {
    return new AnathemaDataItem<ISeries>(new Series());
  }
}