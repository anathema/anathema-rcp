package net.sf.anathema.tools.conversion;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import net.disy.commons.core.io.IOUtilities;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Transform;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;

public class StructureConversionSheet_Test {

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
    assertStructureTransformationConvertsSimilar(xml, expectedResult);
  }

  @Test
  public void retainsCharmReference() throws Exception {
    String xml = "<charmlist><charm><prerequisite><charmReference id=\"Dragon-Blooded.DragonGracedArrow\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    assertStructureTransformationConvertsSimilar(xml, xml);
  }

  @Test
  public void transformsPrerequisiteAnyExcellency() throws Exception {
    String xml = "<charmlist><charm exalt=\"Dragon-Blooded\"><prerequisite><charmAttributeRequirement attribute=\"ExcellencyArchery\" count=\"1\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    String expectedResult = "<charmlist><charm exalt=\"Dragon-Blooded\"><prerequisite><charmReference id=\"dragon-blooded.any.{0}.excellency\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    assertStructureTransformationConvertsSimilar(xml, expectedResult);
  }

  @Test
  public void transformsPrerequisiteAnyExcellencyForOtherCharacterType() throws Exception {
    String xml = "<charmlist><charm exalt=\"Solar\"><prerequisite><charmAttributeRequirement attribute=\"ExcellencyArchery\" count=\"1\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    String expectedResult = "<charmlist><charm exalt=\"Solar\"><prerequisite><charmReference id=\"solar.any.{0}.excellency\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    assertStructureTransformationConvertsSimilar(xml, expectedResult);
  }

  @Test
  public void ignoresNonExcellencyCharmAttributeRequirements() throws Exception {
    String xml = "<charmlist><charm exalt=\"Solar\"><prerequisite><charmAttributeRequirement attribute=\"other\" count=\"1\"/></prerequisite></charm></charmlist>"; //$NON-NLS-1$
    String expectedResult = "<charmlist><charm exalt=\"Solar\"><prerequisite/></charm></charmlist>"; //$NON-NLS-1$
    assertStructureTransformationConvertsSimilar(xml, expectedResult);
  }

  private void assertStructureTransformationConvertsSimilar(String xml, String expectedResult) throws Exception {
    Diff diff = createDiff(xml, expectedResult);
    System.err.println(createTransform(xml).getResultString());
    assertThat(createDiff(xml, expectedResult).toString(), diff.similar(), is(true));
  }

  private Diff createDiff(String xml, String expectedResult) throws Exception {
    Transform transform = createTransform(xml);
    return new Diff(expectedResult, transform);
  }

  private Transform createTransform(String xml) throws IOException {
    XMLUnit.setIgnoreWhitespace(true);
    XMLUnit.setIgnoreAttributeOrder(true);
    InputStream sheetStream = null;
    try {
      Source inputSource = new StreamSource(new StringReader(xml));
      sheetStream = new StructureSheetStreamFactory().createInstance();
      Source sheetSource = new StreamSource(sheetStream);
      return new Transform(inputSource, sheetSource);
    }
    finally {
      IOUtilities.close(sheetStream);
    }
  }
}