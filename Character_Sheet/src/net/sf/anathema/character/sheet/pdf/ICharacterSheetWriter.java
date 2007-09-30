package net.sf.anathema.character.sheet.pdf;

import java.io.OutputStream;

import net.sf.anathema.character.core.character.ICharacter;

import org.eclipse.core.runtime.IProgressMonitor;

import com.lowagie.text.DocumentException;

public interface ICharacterSheetWriter {

  public void write(IProgressMonitor monitor, ICharacter character, OutputStream outputStream) throws DocumentException;
  
  public int getTaskCount();
}