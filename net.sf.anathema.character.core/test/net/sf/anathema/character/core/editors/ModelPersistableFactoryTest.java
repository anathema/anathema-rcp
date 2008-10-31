package net.sf.anathema.character.core.editors;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.basics.item.editor.ErrorMessageEditorInput;
import net.sf.anathema.basics.repository.fake.MementoObjectMother;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IMemento;
import org.junit.Before;
import org.junit.Test;

public class ModelPersistableFactoryTest {

  private ModelPersistableFactory factory;
  private ICharacterTemplateProvider templateProvider;

  @Before
  public void createFactory() {
    templateProvider = EasyMock.createMock(ICharacterTemplateProvider.class);
    IContainer characterFolder = EasyMock.createNiceMock(IContainer.class);
    EasyMock.replay(characterFolder);
    IFile file = ResourceObjectMother.createFile(characterFolder);
    IContainer root = ResourceObjectMother.createContainerWithFileAtArbitraryPath(file);
    this.factory = new ModelPersistableFactory(root, templateProvider);
  }

  @Test
  public void returnsMessageEditorInputForNonExistingTemplate() throws Exception {
    EasyMock.expect(templateProvider.isTemplateAvailable(EasyMock.isA(ICharacterId.class))).andStubReturn(false);
    EasyMock.replay(templateProvider);
    IMemento memento = MementoObjectMother.createStringReturningMemento();
    IAdaptable adaptable = factory.createElement(memento);
    assertTrue(adaptable instanceof ErrorMessageEditorInput);
  }
}