package net.sf.anathema.character.abilities.sheet;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.abilities.util.AbilitiesDisplayUtilties;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.AbstractPdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class AbilitiesEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder, IExecutableExtension {

  private static final BaseFont BASEFONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);
  private final List<IIdentificate> markedTraitTypes = new ArrayList<IIdentificate>();
  private final List<ISubSectionEncoder> subsectionEncoders = new ArrayList<ISubSectionEncoder>();
  private final PdfTraitEncoder traitEncoder;

  public AbilitiesEncoder() {
    traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(BASEFONT);
    Collections.addAll(markedTraitTypes, new Identificate("Athletics"), new Identificate("Dodge"), new Identificate(
        "Larceny"), new Identificate("Ride"), new Identificate("Stealth"));
    addSubsectionEncoder(new EmptySubsectionEncoder(BASEFONT, traitEncoder, "Crafts", 10, 9));
    addSubsectionEncoder(new EmptySubsectionEncoder(BASEFONT, traitEncoder, "Specialities", 3, 9));
  }

  protected final void addSubsectionEncoder(ISubSectionEncoder encoder) {
    subsectionEncoders.add(encoder);
  }

  @Override
  protected BaseFont getBaseFont() {
    return BASEFONT;
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    Position position = new Position(bounds.getMinX(), bounds.getMaxY());
    float width = bounds.width;
    float yPosition = encodeTraitGroups(directContent, character, position, width);
    for (ISubSectionEncoder encoder : subsectionEncoders) {
      yPosition -= IVoidStateFormatConstants.LINE_HEIGHT;
      yPosition -= encoder.encode(directContent, character, new Position(position.x, yPosition), width);
    }
    if (!markedTraitTypes.isEmpty()) {
      encodeMarkerCommentText(directContent, position, bounds.getMinY() + 4);
    }
  }

  private float encodeTraitGroups(PdfContentByte directContent, ICharacter character, Position position, float width) {
    List<IDisplayTraitGroup<IDisplayTrait>> groups = getDisplayTraits(character);
    float yPosition = position.y;
    for (IDisplayTraitGroup<IDisplayTrait> group : groups) {
      Position groupPosition = new Position(position.x, yPosition);
      yPosition -= encodeTraitGroup(directContent, group, groupPosition, width);
      yPosition -= IVoidStateFormatConstants.TEXT_PADDING;
    }
    return yPosition;
  }

  private float encodeTraitGroup(
      PdfContentByte directContent,
      IDisplayTraitGroup<IDisplayTrait> group,
      Position position,
      float width) {
    float height = 0;
    float groupLabelWidth = IVoidStateFormatConstants.LINE_HEIGHT + IVoidStateFormatConstants.TEXT_PADDING;
    float traitX = position.x + groupLabelWidth;
    float groupLabelX = position.x + 4;
    float markerX = groupLabelX + IVoidStateFormatConstants.TEXT_PADDING;
    int index = -1;
    for (IDisplayTrait trait : group.getTraits()) {
      index++;
      float yPosition = position.y - (index + 1) * traitEncoder.getTraitHeight();
      if (markedTraitTypes.contains(trait.getTraitType())) {
        encodeMarker(directContent, new Position(markerX, yPosition + 1));
      }
      // TODO i18n für Abilities
      String label = trait.getTraitType().getId();
      Position traitPosition = new Position(traitX, yPosition);
      height += encodeFavorableTrait(directContent, label, trait, traitPosition, width - groupLabelWidth);
    }
    Position groupLabelPosition = new Position(groupLabelX, position.y - height / 2);
    addGroupLabel(directContent, group, groupLabelPosition);
    return height;
  }

  private void addGroupLabel(PdfContentByte directContent, IDisplayTraitGroup<IDisplayTrait> group, Position position) {
    String groupLabel = group.getId();
    drawVerticalText(directContent, groupLabel, position, PdfContentByte.ALIGN_CENTER);
  }

  private int encodeFavorableTrait(
      PdfContentByte directContent,
      String label,
      IDisplayTrait trait,
      Position position,
      float width) {
    int value = trait.getValue();
    boolean favored = trait.getFavorization().getStatusModel().getStatus().isCheap();
    return traitEncoder.encodeWithTextAndRectangle(
        directContent,
        label,
        position,
        width,
        value,
        favored,
        trait.getMaximalValue());
  }

  private final void encodeMarker(PdfContentByte directContent, Position markerPosition) {
    directContent.setLineWidth(1.0f);
    directContent.moveTo(markerPosition.x, markerPosition.y + 2);
    directContent.lineTo(markerPosition.x + 4, markerPosition.y + 2);
    directContent.moveTo(markerPosition.x + 2, markerPosition.y);
    directContent.lineTo(markerPosition.x + 2, markerPosition.y + 4);
    directContent.stroke();
  }

  private float encodeMarkerCommentText(PdfContentByte directContent, Position position, float yPosition) {
    encodeMarker(directContent, new Position(position.x, yPosition));
    String mobilityPenaltyText = " : This ability is commonly affected by mobility penalty.";
    Position commentPosition = new Position(position.x + 5, yPosition);
    drawComment(directContent, mobilityPenaltyText, commentPosition, PdfContentByte.ALIGN_LEFT);
    return 10;
  }

  @Override
  public String getHeader(ICharacter character) {
    return "Abilities";
  }

  protected List<IDisplayTraitGroup<IDisplayTrait>> getDisplayTraits(ICharacter character) {
    return AbilitiesDisplayUtilties.getDisplayAttributeGroups(character);
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}