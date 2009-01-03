package net.sf.anathema.charms.character.sheet.generic;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class GenericCharmEncoder extends AbstractExecutableExtension implements IPdfContentBoxEncoder {
  @Override
  public String getHeader(ICharacter character) {
    return "Generic Charms";
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    List<String> genericPatterns = collectGenerics(character);
    new GenericCharmTableEncoder(context.getBaseFont(), genericPatterns, character).encodeTable(directContent, bounds);
  }

  private List<String> collectGenerics(ICharacter character) {
    String typeId = character.getCharacterType().getId();
    
    // TODO Auto-generated method stub
    return null;
  }
}