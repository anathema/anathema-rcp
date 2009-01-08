package net.sf.anathema.tools.conversion;

import static net.sf.anathema.test.xml.XMLUnitUtils.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class StructureConversionSheet_Test {

  private File sheet;

  @Before
  public void testname() throws Exception {
    sheet = new File("src/net/sf/anathema/tools/conversion/charmStructure.xslt"); //$NON-NLS-1$
  }

  @Test
  public void getsRidOfAllTheGarbage() throws Exception {
    String xml = "<charmlist>" //$NON-NLS-1$
        + "<charm id=\"Dragon-Blooded.DragonGracedArrow\" exalt=\"Dragon-Blooded\" group=\"Archery\">" //$NON-NLS-1$
        + " <prerequisite>" //$NON-NLS-1$
        + "  <trait id=\"Archery\" value=\"3\"/>" //$NON-NLS-1$
        + "  <essence value=\"2\"/>" //$NON-NLS-1$
        + " </prerequisite>" //$NON-NLS-1$
        + " <cost>" //$NON-NLS-1$
        + "   <essence cost=\"1\"/>" //$NON-NLS-1$
        + " </cost>" //$NON-NLS-1$
        + " <duration duration=\"Instant\"/>" //$NON-NLS-1$
        + " <charmtype type=\"Supplemental\"/>" //$NON-NLS-1$
        + " <charmAttribute attribute=\"Combo-OK\" visualize=\"true\"/>" //$NON-NLS-1$
        + " <charmAttribute attribute=\"Elemental\" visualize=\"true\"/>" //$NON-NLS-1$
        + " <charmAttribute attribute=\"Obvious\" visualize=\"true\"/>" //$NON-NLS-1$
        + "  <source source=\"DragonBlooded2nd\"/>" //$NON-NLS-1$
        + "</charm>" //$NON-NLS-1$
        + "</charmlist>"; //$NON-NLS-1$
    String expectedResult = "<charmlist>" //$NON-NLS-1$
        + "<charm id=\"Dragon-Blooded.DragonGracedArrow\" exalt=\"Dragon-Blooded\" group=\"Archery\">" //$NON-NLS-1$
        + " <prerequisite/>" //$NON-NLS-1$
        + " </charm>" //$NON-NLS-1$
        + "</charmlist>"; //$NON-NLS-1$
    assertStructureTransformationConvertsSimilar(xml, expectedResult, sheet);
  }

  @Test
  public void retainsCharmReference() throws Exception {
    String xml = "<charmlist><charm><prerequisite><charmReference id=\"Dragon-Blooded.DragonGracedArrow\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    assertStructureTransformationConvertsSimilar(xml, xml, sheet);
  }

  @Test
  public void transformsPrerequisiteAnyExcellency() throws Exception {
    String xml = "<charmlist><charm exalt=\"Dragon-Blooded\"><prerequisite><charmAttributeRequirement attribute=\"ExcellencyArchery\" count=\"1\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    String expectedResult = "<charmlist><charm exalt=\"Dragon-Blooded\"><prerequisite><charmReference id=\"dragon-blooded.any.{0}.excellency\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    assertStructureTransformationConvertsSimilar(xml, expectedResult, sheet);
  }

  @Test
  public void transformsPrerequisiteAnyExcellencyForOtherCharacterType() throws Exception {
    String xml = "<charmlist><charm exalt=\"Solar\"><prerequisite><charmAttributeRequirement attribute=\"ExcellencyArchery\" count=\"1\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    String expectedResult = "<charmlist><charm exalt=\"Solar\"><prerequisite><charmReference id=\"solar.any.{0}.excellency\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    assertStructureTransformationConvertsSimilar(xml, expectedResult, sheet);
  }

  @Test
  public void ignoresNonExcellencyCharmAttributeRequirements() throws Exception {
    String xml = "<charmlist><charm exalt=\"Solar\"><prerequisite><charmAttributeRequirement attribute=\"other\" count=\"1\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    String expectedResult = "<charmlist><charm exalt=\"Solar\"><prerequisite/></charm></charmlist>"; //$NON-NLS-1$
    assertStructureTransformationConvertsSimilar(xml, expectedResult, sheet);
  }
}