package net.sf.anathema.character.abilities.sheet;

import static net.sf.anathema.character.abilities.sheet.Messages.*;
import static net.sf.anathema.character.trait.sheet.PdfTraitEncoder.*;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.abilities.model.AbilitiesMessages;
import net.sf.anathema.character.abilities.util.AbilitiesDisplayGroupFactory;
import net.sf.anathema.character.abilities.util.IAbilityIds;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class AbilitiesEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  private final MarkerEncoder markerEncoder = new MarkerEncoder();
  private final MarkedTraits markedTraits = new MarkedTraits();

  private List<ISubSectionEncoder> getOtherSubsectionEncoder(
      PdfTraitEncoder traitEncoder,
      int maxEssence,
      List<IDisplayTraitGroup<IDisplayTrait>> groups) {
    IDisplayTrait craft = new DisplayTraitList<IDisplayTrait>(groups).getTrait(IAbilityIds.CRAFT);
    List<ISubSectionEncoder> subsectionEncoders = new ArrayList<ISubSectionEncoder>();
    subsectionEncoders.add(new CraftsSubsectionEncoder(
        traitEncoder,
        AbilitiesEncoder_CraftsHeader,
        maxEssence,
        9,
        craft));
    subsectionEncoders.add(new EmptySubsectionEncoder(traitEncoder, AbilitiesEncoder_SpecialtiesHeader, 3, 9));
    return subsectionEncoders;
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    final PdfTraitEncoder traitEncoder = createSmallTraitEncoder(new PdfEncoder(directContent));
    Position position = new Position(bounds.getMinX(), bounds.getMaxY());
    float width = bounds.width;
    List<IDisplayTraitGroup<IDisplayTrait>> groups = new AbilitiesDisplayGroupFactory().createDisplayTraitGroups(character);
    float yPosition = encodeTraitGroups(traitEncoder, directContent, groups, position, width);
    for (ISubSectionEncoder encoder : getOtherSubsectionEncoder(traitEncoder, context.getMaxEssence(), groups)) {
      yPosition -= IVoidStateFormatConstants.LINE_HEIGHT;
      yPosition -= encoder.encode(directContent, character, new Position(position.x, yPosition), width);
    }
    encodeMarkerCommentText(directContent, position, bounds.getMinY() + 4);
  }

  private float encodeTraitGroups(
      final PdfTraitEncoder traitEncoder,
      PdfContentByte directContent,
      List<IDisplayTraitGroup<IDisplayTrait>> groups,
      Position position,
      float width) {
    float yPosition = position.y;
    for (IDisplayTraitGroup<IDisplayTrait> group : groups) {
      Position groupPosition = new Position(position.x, yPosition);
      yPosition -= encodeTraitGroup(traitEncoder, directContent, group, groupPosition, width);
      yPosition -= IVoidStateFormatConstants.TEXT_PADDING;
    }
    return yPosition;
  }

  private float encodeTraitGroup(
      final PdfTraitEncoder traitEncoder,
      PdfContentByte directContent,
      IDisplayTraitGroup<IDisplayTrait> group,
      Position position,
      float width) {
    IGraphicalEncoder pdfEncoder = new PdfEncoder(directContent);
    float height = 0;
    float groupLabelWidth = IVoidStateFormatConstants.LINE_HEIGHT + IVoidStateFormatConstants.TEXT_PADDING;
    float traitX = position.x + groupLabelWidth;
    float groupLabelX = position.x + 4;
    float markerX = groupLabelX + IVoidStateFormatConstants.TEXT_PADDING;
    int index = -1;
    for (IDisplayTrait trait : group) {
      index++;
      float yPosition = position.y - (index + 1) * traitEncoder.getTraitHeight();
      if (markedTraits.isMarked(trait)) {
        markerEncoder.encode(directContent, new Position(markerX, yPosition + 1));
      }
      String label = AbilitiesMessages.get(trait.getTraitType().getId());
      Position traitPosition = new Position(traitX, yPosition);
      height += encodeFavorableTrait(traitEncoder, label, trait, traitPosition, width - groupLabelWidth);
    }
    Position groupLabelPosition = new Position(groupLabelX, position.y - height / 2);
    addGroupLabel(pdfEncoder, group, groupLabelPosition);
    return height;
  }

  private void addGroupLabel(IGraphicalEncoder pdfEncoder, IDisplayTraitGroup<IDisplayTrait> group, Position position) {
    String groupLabel = group.getLabel();
    pdfEncoder.drawVerticalText(groupLabel, position, PdfContentByte.ALIGN_CENTER);
  }

  private int encodeFavorableTrait(
      final PdfTraitEncoder traitEncoder,
      String label,
      IDisplayTrait trait,
      Position position,
      float width) {
    int value = trait.getValue();
    boolean favored = trait.getFavorization().getStatus().isCheap();
    int maximalValue = trait.getMaximalValue();
    return traitEncoder.encodeWithTextAndRectangle(label, position, width, value, favored, maximalValue);
  }

  private float encodeMarkerCommentText(PdfContentByte directContent, Position position, float yPosition) {
    markerEncoder.encode(directContent, new Position(position.x, yPosition));
    String mobilityPenaltyText = Messages.AbilitiesEncoder_MarkerComment;
    Position commentPosition = new Position(position.x + 5, yPosition);
    new PdfEncoder(directContent).drawComment(mobilityPenaltyText, commentPosition, PdfContentByte.ALIGN_LEFT);
    return 10;
  }

  @Override
  public String getHeader(ICharacter character) {
    return Messages.AbilitiesEncoder_AbilitiesHeader;
  }
}