package net.sf.anathema.character.core.template;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.FakeExtensionElement;

import org.junit.Before;
import org.junit.Test;

public class FilledCharacterTemplateProviderTest {

  private CharacterTemplateProvider characterTemplateProvider;

  @Before
  public void createTemplateProvider() throws Exception {
    FakeExtensionElement element = new FakeExtensionElement();
    element.addAttribute("templateId", "supportedTemplate"); //$NON-NLS-1$ //$NON-NLS-2$
    element.addElement("model", createModelElement("supportedModel")); //$NON-NLS-1$ //$NON-NLS-2$
    element.addElement("model", createModelElement("otherSupportedModel")); //$NON-NLS-1$ //$NON-NLS-2$
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(element);
    characterTemplateProvider = new CharacterTemplateProvider(new IPluginExtension[] { pluginExtension });
  }
  
  @Test
  public void hasOneTemplate() throws Exception {
    assertEquals(1, characterTemplateProvider.getAllTemplates().size());
  }
  
  @Test
  public void templateHasTemplateId() throws Exception {
    assertEquals("supportedTemplate", getTemplate().getId()); //$NON-NLS-1$
  }

  @Test
  public void templateSupportsSupportedModels() throws Exception {
    assertTrue(getTemplate().supportsModel("supportedModel")); //$NON-NLS-1$
    assertTrue(getTemplate().supportsModel("otherSupportedModel")); //$NON-NLS-1$
  }

  private ICharacterTemplate getTemplate() {
    return characterTemplateProvider.getAllTemplates().get(0);
  }

  private IExtensionElement createModelElement(String modelId) {
    FakeExtensionElement firstModelElement = new FakeExtensionElement();
    firstModelElement.addAttribute("modelId", modelId); //$NON-NLS-1$
    return firstModelElement;
  }
}