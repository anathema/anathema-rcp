package net.sf.anathema.character.sheet.pdf;

import java.io.OutputStream;

import net.sf.anathema.character.core.character.ICharacter;

import com.lowagie.text.DocumentException;

public interface ICharacterSheetWriter {

  public void write(ICharacter character, OutputStream outputStream) throws DocumentException;
}