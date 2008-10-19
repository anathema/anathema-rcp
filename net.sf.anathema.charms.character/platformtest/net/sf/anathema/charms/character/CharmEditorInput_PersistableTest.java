package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

public class CharmEditorInput_PersistableTest {

  @Test
  public void persistableFactoryForCharmsIsRegistered() throws Exception {
    IWorkbench workbench = PlatformUI.getWorkbench();
    IElementFactory factory = workbench.getElementFactory(CharmsEditorInput.PERSISTABLE_FACTORY_ID);
    assertThat(factory, is(instanceOf(CharmModelPersistableFactory.class)));
  }
}