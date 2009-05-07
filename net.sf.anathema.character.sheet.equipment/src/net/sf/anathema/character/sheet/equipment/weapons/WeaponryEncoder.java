package net.sf.anathema.character.sheet.equipment.weapons;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.ISubEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class WeaponryEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    ISubEncoder encoder = new WeaponryTableEncoder(context.getBaseFont());
    encoder.encode(directContent, character, bounds);
  }

  @Override
  public String getHeader(ICharacter character) {
    return "Arsenal";
  }
}