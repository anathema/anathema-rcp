package net.sf.anathema.campaign.plot.report.model;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.item.text.TitledTextPersister;
import net.sf.anathema.campaign.plot.creation.PlotRepositoryUtilities;
import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

public class PlotElement implements IPlotElement {

  public static IFile getEditFile(IPlotPart element, final IContainer container) {
    String fileName = element.getRepositoryId() + "." + PlotRepositoryUtilities.getPlotItemType().getFileExtension(); //$NON-NLS-1$
    return container.getFile(new Path(fileName));
  }

  private final ITitledText content;
  private final IPlotElement[] children;

  public PlotElement(IPlotPart element, final IContainer container) throws PersistenceException, CoreException {
    content = new TitledTextPersister().load(DocumentUtilities.read(getEditFile(element, container).getContents()));
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