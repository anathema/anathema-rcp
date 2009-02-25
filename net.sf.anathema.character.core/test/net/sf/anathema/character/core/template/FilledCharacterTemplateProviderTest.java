package net.sf.anathema.character.core.template;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.FakeExtensionElement;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.fake.TemplateProviderObjectMother;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class FilledCharacterTemplateProviderTest {

  private CharacterTemplateProvider characterTemplateProvider;

  @Before
  public void createTemplateProvider() throws Exception {
    FakeExtensionElement element = new FakeExtensionElement();
    element.addAttribute("templateId", "supportedTemplate");
    element.addAttribute("characterTypeId", "myCharacterTypeId");
    element.addElement("modelList", createModelListElement("supportedModelList"));
    element.addElement("modelList", createModelListElement("otherSupportedModelList"));
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(element);
    DummyModelListProvider modelListProvider = new DummyModelListProvider();
    modelListProvider.addModelList("supportedModelList", TemplateProviderObjectMother.createModelList("supportedModel"));
    modelListProvider.addModelList(
        "otherSupportedModelList",
        TemplateProviderObjectMother.createModelList("otherSupportedModel"));
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
    assertEquals("supportedTemplate", getTemplate().getId());
  }

  @Test
  public void templateHasMyCharacterType() throws Exception {
    assertEquals("myCharacterTypeId", getTemplate().getCharacterTypeId());
  }

  @Test
  public void templateSupportsSupportedModels() throws Exception {
    assertTrue(getTemplate().supportsModel("supportedModel"));
    assertTrue(getTemplate().supportsModel("otherSupportedModel"));
  }

  private ICharacterTemplate getTemplate() {
    return characterTemplateProvider.getAllTemplates().get(0);
  }

  private IExtensionElement createModelListElement(String modelId) {
    FakeExtensionElement firstModelElement = new FakeExtensionElement();
    firstModelElement.addAttribute("modelListId", modelId);
    return firstModelElement;
  }
}