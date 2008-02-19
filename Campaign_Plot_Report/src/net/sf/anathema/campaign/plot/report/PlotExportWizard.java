package net.sf.anathema.campaign.plot.report;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.basics.pdfexport.AbstractPdfExportWizard;
import net.sf.anathema.basics.pdfexport.IReportRunner;
import net.sf.anathema.basics.pdfexport.message.ExportMessages;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.campaign.plot.creation.PlotRepositoryUtilities;
import net.sf.anathema.campaign.plot.report.model.IPlotElement;

import org.eclipse.core.resources.IContainer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;

public class PlotExportWizard extends AbstractPdfExportWizard<IPlotElement> {
  private final static Logger logger = new Logger(PlotPlugin.ID);
  private IPlotElement rootElement;

  @Override
  protected IFileSelectionPageMessages createMessage() {
    return new ExportMessages(Messages.PlotExportWizard_Title);
  }

  @Override
  protected IReportRunner<IPlotElement> createRunner(IOutputStreamFactory outputStreamFactory) {
    return new PlotReportRunner(outputStreamFactory, rootElement);
  }

  @Override
  protected boolean supportsExportItems() {
    return true;
  }

  @Override
  protected List<IExportItem<IPlotElement>> getExportItems() {
    List<IExportItem<IPlotElement>> plotElements = new ArrayList<IExportItem<IPlotElement>>();
    for (IContainer container : PlotRepositoryUtilities.getAllPlotFolders()) {
      try {
        plotElements.add(new PlotExportItem(container));
      }
      catch (Exception e) {
        String format = Messages.PlotReportRunnable_ErrorLoadingPlotMessageFormat;
        logger.error(NLS.bind(format, container), e);
      }
    }
    return plotElements;
  }

  @Override
  protected String getSuggestedName(IEditorPart editor) {
    return "Plot";
  }
}