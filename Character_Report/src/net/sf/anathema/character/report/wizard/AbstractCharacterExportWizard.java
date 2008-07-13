package net.sf.anathema.character.report.wizard;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.pdfexport.AbstractPdfExportWizard;
import net.sf.anathema.basics.pdfexport.IReportRunner;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.report.internal.wizard.CharacterReportRunner;

public abstract class AbstractCharacterExportWizard extends AbstractPdfExportWizard<ICharacter> {

  @Override
  protected IReportRunner<ICharacter> createRunner(IOutputStreamFactory outputStreamFactory) {
    return new CharacterReportRunner(outputStreamFactory, createCharacterPdfWriter());
  }

  @Override
  protected List<IExportItem<ICharacter>> getExportItems() {
    List<IExportItem<ICharacter>> exportItems = new ArrayList<IExportItem<ICharacter>>();
    for (ICharacterId characterId : CharacterRepositoryUtilities.getAllCharacterIds()) {
      exportItems.add(new CharacterExportItem(characterId));
    }
    return exportItems;
  }

  protected abstract IReportWriter<ICharacter> createCharacterPdfWriter();
}