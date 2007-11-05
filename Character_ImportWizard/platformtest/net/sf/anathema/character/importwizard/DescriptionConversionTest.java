package net.sf.anathema.character.importwizard;

import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

public class DescriptionConversionTest {

  @Test
  public void createsDescription() throws Exception {
    DocumentProvider provider = new DocumentProvider(getClass());
    Document document = provider.readDocument("oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = provider.readDocument("newbasic.description"); //$NON-NLS-1$
    Document resultdocument = new CharacterDescriptionConverter().convert(document);
    Assert.assertEquals(DocumentUtilities.asString(expecteddocument), DocumentUtilities.asString(resultdocument));
  }
}