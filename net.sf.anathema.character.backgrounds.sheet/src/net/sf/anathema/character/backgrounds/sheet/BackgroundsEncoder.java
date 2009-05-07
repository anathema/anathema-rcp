package net.sf.anathema.character.backgrounds.sheet;

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

  private static final int BACKGROUND_MAX = 5;
  private PdfTraitEncoder traitEncoder;
  private LineIterator lineIterator;

  @Override
  public String getHeader(ICharacter character) {
    return "Backgrounds";
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    lineIterator = new LineIterator(bounds);
    traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(new PdfEncoder(directContent));
    IBackgroundModel model = (IBackgroundModel) character.getModel(IBackgroundModel.MODEL_ID);
    IExperience experience = (IExperience) character.getModel(IExperience.MODEL_ID);
    encodeBackgrounds(model, experience);
    fillUpWithEmptyLines();
  }

  private void encodeBackgrounds(IBackgroundModel model, IExperience experience) {
    for (IBasicTrait background : model) {
      if (!lineIterator.hasNext()) {
        return;
      }
      if (isEncodedBackground(background, experience)) {
        encodeBackground(background, experience);
      }
    }
  }

  private void encodeBackground(IBasicTrait background, IExperience experience) {
    String backgroundName = background.getTraitType().getId();
    int backgroundValue = getBackgroundValue(background, experience);
    Position lineStart = lineIterator.nextLineStart();
    float lineWidth = lineIterator.getLineWidth();
    traitEncoder.encodeWithText(backgroundName, lineStart, lineWidth, backgroundValue, BACKGROUND_MAX);
  }

  private boolean isEncodedBackground(IBasicTrait background, IExperience experience) {
    int backgroundValue = getBackgroundValue(background, experience);
    return !isPrintZeroBackgrounds() && backgroundValue == 0;
  }

  private int getBackgroundValue(IBasicTrait background, IExperience experience) {
    boolean isExperienced = experience.isExperienced();
    IIntValueModel model = isExperienced ? background.getExperiencedModel() : background.getCreationModel();
    return model.getValue();
  }

  private boolean isPrintZeroBackgrounds() {
    // TODO Case 438: Preferenz wieder einführen
    return true;
  }

  private void fillUpWithEmptyLines() {
    while (lineIterator.hasNext()) {
      Position lineStart = lineIterator.nextLineStart();
      float lineWidth = lineIterator.getLineWidth();
      traitEncoder.encodeWithLine(lineStart, lineWidth, 0, BACKGROUND_MAX);
    }
  }
}