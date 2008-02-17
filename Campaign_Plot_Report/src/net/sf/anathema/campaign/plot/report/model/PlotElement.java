package net.sf.anathema.campaign.plot.report.model;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.item.text.TitledTextPersister;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.campaign.plot.persistence.PlotPersister;
import net.sf.anathema.campaign.plot.report.Messages;
import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.campaign.plot.repository.PlotElementViewElement;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;

public class PlotElement implements IPlotElement {
  private final static Logger logger = new Logger(PlotPlugin.ID);

  public static IPlotElement getRootElement(IEditorPart editorPart) {
    IResource resource = (IResource) editorPart.getEditorInput().getAdapter(IResource.class);
    IContainer parent = resource.getParent();
    try {
      IPlotPart root = new PlotPersister().load(parent);
      return new PlotElement(root, parent);
    }
    catch (Exception e) {
      String format = Messages.PlotReportRunnable_ErrorLoadingPlotMessageFormat;
      logger.error(NLS.bind(format, parent), e);
      return null;
    }
  }

  private final ITitledText content;
  private final IPlotElement[] children;

  public PlotElement(IPlotPart element, final IContainer container) throws PersistenceException, CoreException {
    content = new TitledTextPersister().load(DocumentUtilities.read(PlotElementViewElement.getPlotPartFile(element, container).getContents()));
    children = ArrayUtilities.transform(
        element.getChildren(),
        IPlotElement.class,
        new ITransformer<IPlotPart, IPlotElement>() {
          @Override
          public IPlotElement transform(IPlotPart plotPart) {
            try {
              return new PlotElement(plotPart, container);
            }
            catch (PersistenceException e) {
              throw new RuntimeException(e);
            }
            catch (CoreException e) {
              throw new RuntimeException(e);
            }
          }
        });
  }

  @Override
  public IPlotElement[] getChildren() {
    return children;
  }

  @Override
  public ITitledText getContent() {
    return content;
  }
}