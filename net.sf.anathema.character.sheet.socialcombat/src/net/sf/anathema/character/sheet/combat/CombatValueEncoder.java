package net.sf.anathema.character.sheet.combat;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.derivedtraits.CombatCharacter;
import net.sf.anathema.character.derivedtraits.ICombatCharacter;
import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.content.ISubEncoder;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.socialcombat.LabelledValueEncoder;

import com.lowagie.text.pdf.PdfContentByte;

public class CombatValueEncoder implements ISubEncoder {

  public float encode(PdfContentByte directContent, ICharacter character, Bounds bounds) {
    ICombatCharacter combatCharacter = new CombatCharacter(character);
    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    IGraphicalEncoder graphicalEncoder = new PdfEncoder(directContent);
    LabelledValueEncoder encoder = new LabelledValueEncoder(4, upperLeftCorner, bounds.width, 3);
    encoder.addLabelledValue(graphicalEncoder, 0, "Join Battle", combatCharacter.getJoinBattle());
    encoder.addLabelledValue(graphicalEncoder, 1, "Dodge DV", combatCharacter.getDodgeDv());
    encoder.addLabelledValue(
        graphicalEncoder,
        2,
        "Knockdown",
        combatCharacter.getKnockdownThreshold(),
        combatCharacter.getKnockdownPool());
    encoder.addLabelledValue(
        graphicalEncoder,
        3,
        "Stunning",
        combatCharacter.getStunningThreshold(),
        combatCharacter.getStunningPool());
    encoder.addComment(graphicalEncoder, "-Mob.Pen", 1);
    encoder.addComment(graphicalEncoder, "Threshold / Pool", 2);
    encoder.addComment(graphicalEncoder, "Threshold / Pool", 3);
    return encoder.getHeight();
  }
}