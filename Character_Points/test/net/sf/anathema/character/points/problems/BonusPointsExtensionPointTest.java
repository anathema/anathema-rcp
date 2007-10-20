package net.sf.anathema.character.points.problems;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.IntegerMockAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.StringMockAttribute;

import org.junit.Before;
import org.junit.Test;

public class BonusPointsExtensionPointTest {

  private static final int BONUS_POINT_COUNT = 2;
  private static final String TEMPLATE_ID = "foundTemplate"; //$NON-NLS-1$
  private BonusPointExtensionPoint extensionPoint;

  @Before
  public void createExtensionPoint() throws Exception {
    IntegerMockAttribute amoutAttribute = new IntegerMockAttribute("amount", BONUS_POINT_COUNT); //$NON-NLS-1$
    StringMockAttribute templateIdAttribute = new StringMockAttribute("templateId", TEMPLATE_ID); //$NON-NLS-1$
    IExtensionElement element = ExtensionObjectMother.createExtensionElementWithAttributes(
        amoutAttribute,
        templateIdAttribute);
    extensionPoint = new BonusPointExtensionPoint(ExtensionObjectMother.createPluginExtension(element));
  }

  @Test
  public void zeroBonusPointsForTemplateIdNotFound() throws Exception {
    assertEquals(0, extensionPoint.getBonusPoints("nonExistingTemplateId")); //$NON-NLS-1$
  }
  
  @Test
  public void configuredAmountForFoundTemplateId() throws Exception {
    assertEquals(BONUS_POINT_COUNT, extensionPoint.getBonusPoints(TEMPLATE_ID));
  }
}