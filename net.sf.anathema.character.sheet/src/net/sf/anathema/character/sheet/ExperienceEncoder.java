package net.sf.anathema.character.sheet;

import static net.sf.anathema.character.sheet.common.IEncodeContext.*;
import static net.sf.anathema.character.sheet.content.PdfTextEncodingUtilities.*;

import java.text.MessageFormat;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.points.calculation.ExperienceCharacter;
import net.sf.anathema.character.points.calculation.IExperienceCharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;

public class ExperienceEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  private static final String EXPERIENCE_PATTERN = "{0} total - {1} spent = {2} banked";

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    IExperienceCharacter experienceCharacter = ExperienceCharacter.CreateFromPlatform(character);
    int totalPoints = experienceCharacter.getLifetimeXp();
    int spentPoints = experienceCharacter.getSpentXp();
    int bankedPoints = experienceCharacter.getBankedXp();
    String experienceText = MessageFormat.format(EXPERIENCE_PATTERN, totalPoints, spentPoints, bankedPoints);
    Phrase phrase = new Phrase(experienceText, createFont(context.getBaseFont(), FONT_SIZE));
    encodeText(directContent, phrase, bounds, FONT_SIZE + 6);

  }

  @Override
  public String getHeader(ICharacter character) {
    return "Experience";
  }
}