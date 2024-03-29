package net.sf.anathema.character.core.fake;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.model.list.IModelList;
import net.sf.anathema.character.core.model.list.ModelList;

import org.easymock.EasyMock;

public class TemplateProviderObjectMother {

  public static ICharacterTemplateProvider createTemplateProvider(ICharacterId characterId, String templateId) {
    ICharacterTemplate template = createTemplate(templateId);
    ICharacterTemplateProvider templateProvider = createTemplateProvider(characterId, template);
    return templateProvider;
  }

  public static ICharacterTemplate createTemplate(String templateId) {
    ICharacterTemplate template = EasyMock.createMock(ICharacterTemplate.class);
    EasyMock.expect(template.getId()).andReturn(templateId).anyTimes();
    EasyMock.replay(template);
    return template;
  }

  public static ICharacterTemplateProvider createTemplateProvider(ICharacterId characterId, ICharacterTemplate template) {
    ICharacterTemplateProvider templateProvider = EasyMock.createMock(ICharacterTemplateProvider.class);
    EasyMock.expect(templateProvider.getTemplate(characterId)).andReturn(template).anyTimes();
    EasyMock.replay(templateProvider);
    return templateProvider;
  }

  public static DummyCharacterId createCharacterId(String templateId) {
    DummyCharacterId characterId = new DummyCharacterId();
    characterId.contentHandlesByName.put("template.xml", new DummyContentHandler("<template reference=\"" //$NON-NLS-1$ //$NON-NLS-2$
        + templateId
        + "\" />")); //$NON-NLS-1$
    return characterId;
  }

  public static IModelList createModelList(String modelId) {
    ModelList modelList = new ModelList(null);
    modelList.addModelId(modelId);
    return modelList;
  }
}