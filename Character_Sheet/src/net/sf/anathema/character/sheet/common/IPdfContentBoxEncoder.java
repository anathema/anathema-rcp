package net.sf.anathema.character.sheet.common;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;

import org.eclipse.core.runtime.IExecutableExtension;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfContentBoxEncoder extends IExecutableExtension {

  public void encode(PdfContentByte directContent, ICharacter character, Bounds bounds) throws DocumentException;

  public String getHeader();
}