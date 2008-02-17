package net.sf.anathema.character.report.internal.wizard;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.report.wizard.IReportWriter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import com.lowagie.text.DocumentException;

public final class CharacterReportRunnable implements IRunnableWithProgress {
  private final IEditorPart editorPart;
  private final OutputStream outputStream;
  private final IReportWriter writer;

  public CharacterReportRunnable(IEditorPart editorPart, OutputStream outputStream, IReportWriter writer) {
    this.editorPart = editorPart;
    this.outputStream = outputStream;
    this.writer = writer;
  }

  @Override
  public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
    try {
      monitor.beginTask(Messages.CharacterReportRunnable_MainTaskSheet, 1 + writer.getTaskCount());
      monitor.subTask(Messages.CharacterReportRunnable_SubTaskCreateCharacter);
      ICharacter character = createCharacter();
      monitor.worked(1);
      writer.write(monitor, character, outputStream);
    }
    catch (DocumentException e) {
      throw new InvocationTargetException(e);
    }
    finally {
      monitor.done();
    }
  }

  private ICharacter createCharacter() {
    IEditorInput editorInput = editorPart.getEditorInput();
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    return new Character(
        identifier.getCharacterId(),
        ModelCache.getInstance(),
        new CharacterTemplateProvider(),
        new CharacterTypeFinder());
  }
}