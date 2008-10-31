package net.sf.anathema.campaign.note.report;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.pdfexport.AbstractPdfExportWizard;
import net.sf.anathema.basics.pdfexport.IReportRunner;
import net.sf.anathema.basics.pdfexport.message.ExportMessages;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.campaign.note.NotesRepositoryUtilities;
import net.sf.anathema.campaign.note.plugin.NotePluginConstants;

import org.eclipse.core.resources.IFile;
import org.eclipse.osgi.util.NLS;

public class NoteExportWizard extends AbstractPdfExportWizard<ITitledText> {
  public static final Logger logger = new Logger(NotePluginConstants.PLUGIN_ID);

  @Override
  protected IFileSelectionPageMessages createMessage() {
    return new ExportMessages(Messages.NoteExportWizard_Title);
  }

  @Override
  protected IReportRunner<ITitledText> createRunner(IOutputStreamFactory outputStreamFactory) {
    return new NoteReportRunner(outputStreamFactory);
  }

  @Override
  protected List<IExportItem<ITitledText>> getExportItems() {
    List<IExportItem<ITitledText>> exportItems = new ArrayList<IExportItem<ITitledText>>();
    for (IFile notesFile : NotesRepositoryUtilities.getNoteFiles()) {
      try {
        exportItems.add(new NoteExportItem(notesFile));
      }
      catch (Exception e) {
        logger.error(NLS.bind(Messages.NoteExportWizard_ErrorMessage, notesFile), e);
      }
    }
    return exportItems;
  }
}