package net.sf.anathema.character.sheet.combat;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.ISubEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class CombatStatsEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  private final static float PADDING = 3;

  @Override
  public String getHeader(ICharacter character) {
    return "Combat";
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    ISubEncoder combatValueEncoder = new CombatValueEncoder();
    float height = combatValueEncoder.encode(directContent, character, bounds);
    Bounds ruleBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - height - PADDING);
    ISubEncoder combatRulesEncoder = new CombatRulesTableEncoder(context.getBaseFont());
    combatRulesEncoder.encode(directContent, character, ruleBounds);
  }
}