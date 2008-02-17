package net.sf.anathema.character.report.internal.wizard;

import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunnable;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.type.CharacterTypeFinder;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

public final class CharacterReportRunnable extends AbstractReportRunnable<ICharacter> {
  private final IEditorPart editorPart;

  public CharacterReportRunnable(IEditorPart editorPart, OutputStream outputStream, IReportWriter<ICharacter> writer) {
    super(outputStream, writer);
    this.editorPart = editorPart;
  }

  @Override
  protected ICharacter createItem() {
    IEditorInput editorInput = editorPart.getEditorInput();
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    return new Character(
        identifier.getCharacterId(),
        ModelCache.getInstance(),
        new CharacterTemplateProvider(),
        new CharacterTypeFinder());
  }
}