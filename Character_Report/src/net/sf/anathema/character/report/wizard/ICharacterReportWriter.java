package net.sf.anathema.character.report.wizard;

import java.io.OutputStream;

import net.sf.anathema.character.core.character.ICharacter;

import org.eclipse.core.runtime.IProgressMonitor;

import com.lowagie.text.DocumentException;

public interface ICharacterReportWriter {

  public void write(IProgressMonitor monitor, ICharacter character, OutputStream outputStream) throws DocumentException;
  
  public int getTaskCount();
}