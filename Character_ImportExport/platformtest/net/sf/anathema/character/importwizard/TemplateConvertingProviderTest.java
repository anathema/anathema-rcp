package net.sf.anathema.character.importwizard;

import static org.junit.Assert.*;
import net.sf.anathema.character.importwizard.internal.TemplateConverter;
import net.sf.anathema.character.importwizard.utility.ImportDocumentObjectMother;

import org.dom4j.Document;
import org.junit.Test;

public class TemplateConvertingProviderTest {

  @Test
  public void convertsTemplateName() throws Exception {
    Document document = ImportDocumentObjectMother.createDocumentForTemplate("Solar", "TemplateType.Default"); //$NON-NLS-1$ //$NON-NLS-2$
    TemplateConverter provider = new TemplateConverter(document);
    assertEquals("net.sf.anathema.character.template.defaultsolar", provider.get()); //$NON-NLS-1$
  }

  @Test
  public void convertsDifferentTemplateName() throws Exception {
    Document document = ImportDocumentObjectMother.createDocumentForTemplate("Sidereal", "TemplateType.Default"); //$NON-NLS-1$ //$NON-NLS-2$
    TemplateConverter provider = new TemplateConverter(document);
    assertEquals("net.sf.anathema.character.template.defaultsidereal", provider.get()); //$NON-NLS-1$
  }

  @Test
  public void returnsNullForUnknownTemplate() throws Exception {
    Document document = ImportDocumentObjectMother.createDocumentForTemplate("Sidereal", "Has�nTum"); //$NON-NLS-1$ //$NON-NLS-2$
    TemplateConverter provider = new TemplateConverter(document);
    assertNull(provider.get());
  }

  @Test
  public void returnsNullForUnknownType() throws Exception {
    Document document = ImportDocumentObjectMother.createDocumentForTemplate("Has�", "TemplateType.Default"); //$NON-NLS-1$ //$NON-NLS-2$
    TemplateConverter provider = new TemplateConverter(document);
    assertNull(provider.get());
  }
}