package net.sf.anathema.character.core.template;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.FakeExtensionElement;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.fake.TemplateProviderObjectMother;
import net.sf.anathema.character.core.modellist.ModelListProvider;

import org.junit.Before;
import org.junit.Test;

public class FilledCharacterTemplateProviderTest {

  private CharacterTemplateProvider characterTemplateProvider;

  @Before
  public void createTemplateProvider() throws Exception {
    FakeExtensionElement element = new FakeExtensionElement();
    element.addAttribute("templateId", "supportedTemplate"); //$NON-NLS-1$ //$NON-NLS-2$
    element.addAttribute("characterTypeId", "myCharacterTypeId"); //$NON-NLS-1$ //$NON-NLS-2$
    element.addElement("modelList", createModelListElement("supportedModelList")); //$NON-NLS-1$ //$NON-NLS-2$
    element.addElement("modelList", createModelListElement("otherSupportedModelList")); //$NON-NLS-1$ //$NON-NLS-2$
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(element);
    ModelListProvider modelListProvider = new ModelListProvider();
    modelListProvider.addModelList(
        "supportedModelList", //$NON-NLS-1$
        TemplateProviderObjectMother.createModelList("supportedModel")); //$NON-NLS-1$
    modelListProvider.addModelList(
        "otherSupportedModelList", //$NON-NLS-1$
        TemplateProviderObjectMother.createModelList("otherSupportedModel")); //$NON-NLS-1$
    characterTemplateProvider = new CharacterTemplateProvider(
        new IPluginExtension[] { pluginExtension },
        modelListProvider);
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
  public void templateHasMyCharacterType() throws Exception {
    assertEquals("myCharacterTypeId", getTemplate().getCharacterTypeId()); //$NON-NLS-1$
  }

  @Test
  public void templateSupportsSupportedModels() throws Exception {
    assertTrue(getTemplate().supportsModel("supportedModel")); //$NON-NLS-1$
    assertTrue(getTemplate().supportsModel("otherSupportedModel")); //$NON-NLS-1$
  }

  private ICharacterTemplate getTemplate() {
    return characterTemplateProvider.getAllTemplates().get(0);
  }

  private IExtensionElement createModelListElement(String modelId) {
    FakeExtensionElement firstModelElement = new FakeExtensionElement();
    firstModelElement.addAttribute("modelListId", modelId); //$NON-NLS-1$
    return firstModelElement;
  }
}