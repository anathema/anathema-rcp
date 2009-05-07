package net.sf.anathema.character.backgrounds.sheet;

import static net.sf.anathema.character.sheet.page.IVoidStateFormatConstants.*;
import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.backgrounds.model.IBackgroundModel;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.interactive.IIntValueModel;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class BackgroundsEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public String getHeader(ICharacter character) {
    return "Backgrounds";
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    IBackgroundModel model = (IBackgroundModel) character.getModel(IBackgroundModel.MODEL_ID);
    IExperience experience = (IExperience) character.getModel(IExperience.MODEL_ID);
    PdfTraitEncoder traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(new PdfEncoder(directContent));
    float yPosition = bounds.getMaxY() - LINE_HEIGHT;
    // TODO Case 438: Preferenz wieder einführen
    boolean printZeroBackgrounds = true;
    for (IBasicTrait background : model) {
      if (yPosition < bounds.getMinY()) {
        return;
      }
      int value = getBackgroundValue(background, experience);
      boolean printCurrentBackground = !printZeroBackgrounds && value == 0;
      if (printCurrentBackground) {
        continue;
      }
      String backgroundName = background.getTraitType().getId();
      Position position = new Position(bounds.x, yPosition);
      traitEncoder.encodeWithText(backgroundName, position, bounds.width, value, 5);
      yPosition -= LINE_HEIGHT;
    }
    encodeEmptyLines(traitEncoder, bounds, yPosition);
  }

  private int getBackgroundValue(IBasicTrait background, IExperience experience) {
    boolean isExperienced = experience.isExperienced();
    IIntValueModel model = isExperienced ? background.getExperiencedModel() : background.getCreationModel();
    return model.getValue();
  }

  private void encodeEmptyLines(PdfTraitEncoder traitEncoder, Bounds bounds, float yPosition) {
    while (yPosition > bounds.getMinY()) {
      Position position = new Position(bounds.x, yPosition);
      traitEncoder.encodeWithLine(position, bounds.width, 0, 5);
      yPosition -= LINE_HEIGHT;
    }
  }
}