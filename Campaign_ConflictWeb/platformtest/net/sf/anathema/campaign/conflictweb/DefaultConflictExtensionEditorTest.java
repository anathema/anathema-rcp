package net.sf.anathema.campaign.conflictweb;

import static org.junit.Assert.*;

import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.PlatformUI;
import org.junit.Before;
import org.junit.Test;

public class DefaultConflictExtensionEditorTest {

  private IEditorDescriptor defaultEditor;

  @Before
  public void findConflictDefaultEditor() {
    defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(
        "War." + ConflictWebIntegrationConstants.CONFLICT_WEB_FILE_EXTENSION); //$NON-NLS-1$
  }

  @Test
  public void editorIsRegistered() throws Exception {
    assertNotNull(defaultEditor);
  }

  @Test
  public void editorIdIsConflictWebEditorId() throws Exception {
    assertEquals("net.sf.anathema.editors.conflictweb", defaultEditor.getId()); //$NON-NLS-1$
  }
}