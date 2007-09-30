package net.sf.anathema.character.sheet.pdf;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import net.sf.anathema.character.core.character.Character;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import com.lowagie.text.DocumentException;

public final class CharacterSheetRunnable implements IRunnableWithProgress {
  private final IEditorPart editorPart;
  private final OutputStream outputStream;
  private final ICharacterSheetWriter writer;

  public CharacterSheetRunnable(IEditorPart editorPart, OutputStream outputStream, ICharacterSheetWriter writer) {
    this.editorPart = editorPart;
    this.outputStream = outputStream;
    this.writer = writer;
  }

  @Override
  public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
    try {
      monitor.beginTask("Creating Character Sheet.", 1 + writer.getTaskCount());
      monitor.subTask("Preparing Character");
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
    return new Character(identifier.getCharacterId(), ModelCache.getInstance());
  }
}