package net.sf.anathema.test.xml;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Transform;
import org.custommonkey.xmlunit.XMLUnit;

public class XMLUnitUtils {

  private static Diff createXslDiff(String inputXml, String expectedResult, File sheet) throws Exception {
    return new Diff(expectedResult, createTransform(inputXml, sheet));
  }

  private static Transform createTransform(String xml, File sheet) {
    XMLUnit.setIgnoreWhitespace(true);
    XMLUnit.setIgnoreAttributeOrder(true);
    return new Transform(xml, sheet);
  }

  public static void assertTransformationConvertsSimilar(String xml, String expectedResult, File sheet)
      throws Exception {
    Diff diff = XMLUnitUtils.createXslDiff(xml, expectedResult, sheet);
    System.err.println(XMLUnitUtils.createTransform(xml, sheet).getResultString());
    assertThat(XMLUnitUtils.createXslDiff(xml, expectedResult, sheet).toString(), diff.similar(), is(true));
  }
}
