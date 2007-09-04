package net.sf.anathema.character.core.fake;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.modellist.IModelList;
import net.sf.anathema.character.core.modellist.ModelList;
import net.sf.anathema.character.core.template.ICharacterTemplate;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;

import org.easymock.EasyMock;

public class TemplateProviderObjectMother {

  public static ICharacterTemplateProvider createTemplateProvider(ICharacterId characterId, String templateId) {
    ICharacterTemplateProvider templateProvider = EasyMock.createMock(ICharacterTemplateProvider.class);
    ICharacterTemplate template = EasyMock.createMock(ICharacterTemplate.class);
    EasyMock.expect(templateProvider.getTemplate(characterId)).andReturn(template).anyTimes();
    EasyMock.expect(template.getId()).andReturn(templateId).anyTimes();
    EasyMock.replay(template, templateProvider);
    return templateProvider;
  }

  public static DummyCharacterId createCharacterId(String templateId) {
    DummyCharacterId characterId = new DummyCharacterId();
    characterId.addContentHandle("template.xml", new DummyContentHandler("<template reference=\"" //$NON-NLS-1$ //$NON-NLS-2$
        + templateId
        + "\" />")); //$NON-NLS-1$
    return characterId;
  }

  public static IModelList createModelList(String modelId) {
    ModelList modelList = new ModelList();
    modelList.addModelId(modelId);
    return modelList;
  }
}