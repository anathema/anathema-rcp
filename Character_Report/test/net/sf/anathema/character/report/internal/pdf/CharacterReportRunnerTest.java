package net.sf.anathema.character.report.internal.pdf;

import static org.easymock.EasyMock.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.basics.swt.file.IStreamResult;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;

import org.easymock.EasyMock;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.ui.IEditorPart;
import org.junit.Test;

public class CharacterReportRunnerTest {

  @Test
  public void noWritingWithoutOutputStream() throws Exception {
    IOutputStreamFactory outputStreamFactory = createOutputStreamFactory(null);
    IRunnableContext runnableContext = EasyMock.createMock(IRunnableContext.class);
    EasyMock.replay(runnableContext);
    new CharacterReportRunner(outputStreamFactory, null).runWriting(null, null, runnableContext);
  }

  @Test
  public void runnableContextIsCalledWithCorrectOutputStream() throws Exception {
    OutputStream outputStream = new ByteArrayOutputStream();
    IOutputStreamFactory outputStreamFactory = createOutputStreamFactory(outputStream);
    IEditorPart editorPart = EditorPartObjectMother.createEditorPart(CharacterObjectMother.createCharacterEditorInput(new DummyCharacterId()));
    CharacterReportRunner runner = new CharacterReportRunner(outputStreamFactory, null);
    IRunnableContext runnableContext = EasyMock.createNiceMock(IRunnableContext.class);
    runnableContext.run(EasyMock.eq(true), EasyMock.eq(false), EasyMock.isA(CharacterReportRunnable.class));
    EasyMock.replay(runnableContext);
    runner.runWriting(null, editorPart, runnableContext);
    EasyMock.verify(runnableContext);
  }

  private IOutputStreamFactory createOutputStreamFactory(OutputStream outputStream) throws Exception {
    IOutputStreamFactory outputStreamFactory = EasyMock.createMock(IOutputStreamFactory.class);
    IStreamResult streamResult = createMock(IStreamResult.class);
    expect(streamResult.createStream()).andReturn(outputStream);
    replay(streamResult);
    EasyMock.expect(outputStreamFactory.create(null)).andReturn(streamResult).anyTimes();
    EasyMock.replay(outputStreamFactory);
    return outputStreamFactory;
  }
}