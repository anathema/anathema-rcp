package net.sf.anathema.campaign.note.report;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.item.text.TitledTextPersister;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.campaign.note.NotesRepositoryUtilities;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class NoteExportItem implements IExportItem<ITitledText> {

  private final ITitledText note;

  public NoteExportItem(IFile resource) throws PersistenceException, CoreException {
    note = new TitledTextPersister().load(DocumentUtilities.read(resource.getContents()));
  }

  @Override
  public ITitledText createItem() {
    return note;
  }

  @Override
  public String getPrintName() {
    String text = note.getName().getText();
    return StringUtilities.isNullOrTrimmedEmpty(text)
        ? NotesRepositoryUtilities.getNotesItemType().getUntitledName()
        : text;
  }
}