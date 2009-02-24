package net.sf.anathema.character.points.problems;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockIntegerAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

import org.junit.Before;
import org.junit.Test;

public class PointEvaluationsExtensionPointTest {

  private static final int BONUS_POINT_COUNT = 2;
  private static final String TEMPLATE_ID = "foundTemplate"; //$NON-NLS-1$
  private IBonusPointContainer extensionPoint;

  @Before
  public void createExtensionPoint() throws Exception {
    MockIntegerAttribute amoutAttribute = new MockIntegerAttribute("amount", BONUS_POINT_COUNT); //$NON-NLS-1$
    MockStringAttribute templateIdAttribute = new MockStringAttribute("templateId", TEMPLATE_ID); //$NON-NLS-1$
    IExtensionElement element = ExtensionObjectMother.createExtensionElement(
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