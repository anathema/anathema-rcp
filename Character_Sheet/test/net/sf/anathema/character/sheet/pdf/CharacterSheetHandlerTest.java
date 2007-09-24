package net.sf.anathema.character.sheet.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorPart;
import org.junit.Before;
import org.junit.Test;

public class CharacterSheetHandlerTest {

  private ICharacterSheetWriter writer;

  @Before
  public void createWriter() throws Exception {
    writer = EasyMock.createMock(ICharacterSheetWriter.class);
  }

  @Test
  public void noWritingWithoutOutputStream() throws Exception {
    IOutputStreamFactory outputStreamFactory = createOutputStreamFactory(null);
    EasyMock.replay(writer);
    new CharacterSheetHandler(outputStreamFactory, writer).writeToOutput(null, null);
    EasyMock.verify(writer);
  }

  @Test
  public void writerIsCalledWithCorrectOutputStream() throws Exception {
    OutputStream outputStream = new ByteArrayOutputStream();
    IOutputStreamFactory outputStreamFactory = createOutputStreamFactory(outputStream);
    writer.write(EasyMock.isA(ICharacter.class), EasyMock.same(outputStream));
    EasyMock.replay(writer);
    IEditorPart editorPart = EditorPartObjectMother.createEditorPart(CharacterObjectMother.createCharacterEditorInput(new DummyCharacterId()));
    new CharacterSheetHandler(outputStreamFactory, writer).writeToOutput(null, editorPart);
    EasyMock.verify(writer);
  }

  private IOutputStreamFactory createOutputStreamFactory(OutputStream outputStream) throws FileNotFoundException {
    IOutputStreamFactory outputStreamFactory = EasyMock.createMock(IOutputStreamFactory.class);
    EasyMock.expect(outputStreamFactory.create(null)).andReturn(outputStream).anyTimes();
    EasyMock.replay(outputStreamFactory);
    return outputStreamFactory;
  }
}