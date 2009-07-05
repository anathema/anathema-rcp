package net.sf.anathema.tools.conversion;

import static net.sf.anathema.test.xml.XMLUnitUtils.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class StructureConversionSheet_Test {

  private File sheet;

  @Before
  public void testname() throws Exception {
    sheet = new File("src/net/sf/anathema/tools/conversion/charmTraits.xslt");
  }

  @Test
  public void getsRidOfAllTheGarbage() throws Exception {
    String xml = "<charmlist>"
        + "<charm id=\"Dragon-Blooded.DragonGracedArrow\" exalt=\"Dragon-Blooded\" group=\"Archery\">"
        + " <prerequisite>"
        + "  <trait id=\"Archery\" value=\"3\"/>"
        + "  <essence value=\"2\"/>"
        + " </prerequisite>"
        + " <cost>"
        + "   <essence cost=\"1\"/>"
        + " </cost>"
        + " <duration duration=\"Instant\"/>"
        + " <charmtype type=\"Supplemental\"/>"
        + " <charmAttribute attribute=\"Combo-OK\" visualize=\"true\"/>"
        + " <charmAttribute attribute=\"Elemental\" visualize=\"true\"/>"
        + " <charmAttribute attribute=\"Obvious\" visualize=\"true\"/>"
        + "  <source source=\"DragonBlooded2nd\"/>"
        + "</charm>"
        + "</charmlist>";
    String expectedResult = "<charmtraits>"
        + "<charmTraits id=\"Dragon-Blooded.DragonGracedArrow\" essenceMinimum=\"2\" primaryTrait=\"2\"/>"
        + " <prerequisite/>"
        + " </charm>"
        + "</charmtraits>";
    assertTransformationConvertsSimilar(xml, expectedResult, sheet);
  }

  @Test
  public void retainsCharmReference() throws Exception {
    String xml = "<charmlist><charm><prerequisite><charmReference id=\"Dragon-Blooded.DragonGracedArrow\"/></prerequisite></charm></charmlist>";
    assertTransformationConvertsSimilar(xml, xml, sheet);
  }

  @Test
  public void transformsPrerequisiteAnyExcellency() throws Exception {
    String xml = "<charmlist><charm exalt=\"Dragon-Blooded\"><prerequisite><charmAttributeRequirement attribute=\"ExcellencyArchery\" count=\"1\"/></prerequisite></charm></charmlist>";
    String expectedResult = "<charmlist><charm exalt=\"Dragon-Blooded\"><prerequisite><charmReference id=\"dragon-blooded.any.{0}.excellency\"/></prerequisite></charm></charmlist>";
    assertTransformationConvertsSimilar(xml, expectedResult, sheet);
  }

  @Test
  public void transformsPrerequisiteAnyExcellencyForOtherCharacterType() throws Exception {
    String xml = "<charmlist><charm exalt=\"Solar\"><prerequisite><charmAttributeRequirement attribute=\"ExcellencyArchery\" count=\"1\"/></prerequisite></charm></charmlist>";
    String expectedResult = "<charmlist><charm exalt=\"Solar\"><prerequisite><charmReference id=\"solar.any.{0}.excellency\"/></prerequisite></charm></charmlist>";
    assertTransformationConvertsSimilar(xml, expectedResult, sheet);
  }

  @Test
  public void ignoresNonExcellencyCharmAttributeRequirements() throws Exception {
    String xml = "<charmlist><charm exalt=\"Solar\"><prerequisite><charmAttributeRequirement attribute=\"other\" count=\"1\"/></prerequisite></charm></charmlist>";
    String expectedResult = "<charmlist><charm exalt=\"Solar\"><prerequisite/></charm></charmlist>";
    assertTransformationConvertsSimilar(xml, expectedResult, sheet);
  }
}