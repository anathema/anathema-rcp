package net.sf.anathema.character.sheet.pdf;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;

import org.easymock.EasyMock;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorPart;
import org.junit.Before;
import org.junit.Test;

public class CharacterSheetRunnableTest {

  private ICharacterSheetWriter writer;

  @Before
  public void createWriter() throws Exception {
    writer = EasyMock.createMock(ICharacterSheetWriter.class);
  }

  private IProgressMonitor createNiceProgressMonitor() {
    IProgressMonitor monitor = EasyMock.createNiceMock(IProgressMonitor.class);
    EasyMock.replay(monitor);
    return monitor;
  }

  @Test
  public void writerIsCalledWithCorrectOutputStream() throws Exception {
    OutputStream outputStream = new ByteArrayOutputStream();
    IProgressMonitor progressMonitor = createNiceProgressMonitor();
    EasyMock.expect(writer.getTaskCount()).andReturn(1);
    writer.write(EasyMock.same(progressMonitor), EasyMock.isA(ICharacter.class), EasyMock.same(outputStream));
    EasyMock.replay(writer);
    IEditorPart editorPart = EditorPartObjectMother.createEditorPart(CharacterObjectMother.createCharacterEditorInput(new DummyCharacterId()));
    CharacterSheetRunnable runner = new CharacterSheetRunnable(editorPart, outputStream, writer);
    runner.run(progressMonitor);
    EasyMock.verify(writer);
  }
}