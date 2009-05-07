package net.sf.anathema.character.sheet.equipment.armour;

import java.awt.Color;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.ISubEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class ArmourEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    ISubEncoder armourEncoder = new ArmourTableEncoder(context.getBaseFont());
    float armourHeight = armourEncoder.encode(directContent, character, bounds);
    float remainingHeight = bounds.getHeight() - armourHeight;
    float delimitingLineYPosition = bounds.getMinY() + remainingHeight - 1;
    drawDelimiter(directContent, bounds, delimitingLineYPosition);
    Bounds shieldBounds = new Bounds(
        bounds.getMinX(),
        delimitingLineYPosition - 12,
        bounds.getWidth(),
        remainingHeight - 2);
    ISubEncoder shieldEncoder = new ShieldTableEncoder(context.getBaseFont());
    shieldEncoder.encode(directContent, character, shieldBounds);
  }

  private void drawDelimiter(PdfContentByte directContent, Bounds bounds, float delimitingLineYPosition) {
    directContent.moveTo(bounds.getMinX() + 3, delimitingLineYPosition);
    directContent.lineTo(bounds.getMaxX() - 3, delimitingLineYPosition);
    directContent.setColorStroke(Color.GRAY);
    directContent.setLineWidth(0.75f);
    directContent.stroke();
    directContent.setColorStroke(Color.BLACK);
  }

  @Override
  public String getHeader(ICharacter character) {
    return "Panoply";
  }
}