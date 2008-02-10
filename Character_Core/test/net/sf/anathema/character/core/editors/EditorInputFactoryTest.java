package net.sf.anathema.character.core.editors;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IContainer;
import org.junit.Test;

public class EditorInputFactoryTest {

  @Test
  public void createsWithModelDisplayNameProvider() throws Exception {
    IContainer folder = EasyMock.createNiceMock(IContainer.class);
    EasyMock.expect(folder.getName()).andReturn("Character").anyTimes(); //$NON-NLS-1$
    IModelDisplayConfiguration displayConfiguration = EasyMock.createNiceMock(IModelDisplayConfiguration.class);
    IDisplayNameProvider provider = new ModelDisplayNameProvider("Model", new CharacterDisplayNameProvider(folder)); //$NON-NLS-1$
    EasyMock.expect(displayConfiguration.getDisplayName()).andReturn("Model"); //$NON-NLS-1$
    EasyMock.expect(displayConfiguration.createEditorInput(EasyMock.same(folder), EasyMock.eq(provider))).andReturn(
        null);
    EasyMock.replay(displayConfiguration, folder);
    new EditorInputFactory().create(folder, displayConfiguration);
    EasyMock.verify(displayConfiguration);
  }
}