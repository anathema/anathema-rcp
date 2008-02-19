package net.sf.anathema.character.report.internal.wizard;

import static org.easymock.EasyMock.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;
import net.sf.anathema.character.core.character.ICharacter;

import org.easymock.EasyMock;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Before;
import org.junit.Test;

public class CharacterReportRunnableTest {

  private IReportWriter<ICharacter> writer;
  private ICharacter character;
  private IExportItem<ICharacter> exportItem;

  @SuppressWarnings("unchecked")
  @Before
  public void createWriter() throws Exception {
    writer = EasyMock.createMock(IReportWriter.class);
  }
  
  @SuppressWarnings("unchecked")
  @Before
  public void createExportItem() throws Exception {
    character = createMock(ICharacter.class);
    exportItem = createMock(IExportItem.class);
    expect(exportItem.createItem()).andReturn(character).anyTimes();
    replay(exportItem);
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
    writer.write(same(progressMonitor), same(character), same(outputStream));
    EasyMock.replay(writer);
    CharacterReportRunnable runner = new CharacterReportRunnable(exportItem, outputStream, writer);
    runner.run(progressMonitor);
    EasyMock.verify(writer);
  }
}