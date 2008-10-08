package net.sf.anathema.character.core.editors;

import static org.easymock.EasyMock.*;

import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;

import org.eclipse.core.resources.IContainer;
import org.junit.Test;

public class EditorInputFactoryTest {

  @Test
  public void createsWithModelDisplayNameProvider() throws Throwable {
    IContainer folder = createNiceMock(IContainer.class);
    expect(folder.getName()).andReturn("Character").anyTimes(); //$NON-NLS-1$
    IModelDisplayConfiguration displayConfiguration = createNiceMock(IModelDisplayConfiguration.class);
    expect(displayConfiguration.getDisplayName()).andReturn("Model"); //$NON-NLS-1$
    expect(displayConfiguration.createEditorInput(same(folder), isA(ModelDisplayNameProvider.class))).andReturn(null);
    replay(displayConfiguration, folder);
    SilentTestLogger silentTestLogger = new SilentTestLogger();
    new EditorInputFactory(silentTestLogger, null).create(folder, displayConfiguration);
    verify(displayConfiguration);
    silentTestLogger.verify();
  }
}