package net.sf.anathema.character.core.editors;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.model.IModelIdentifier;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class ModelIdentiferProviderTest {

  private IEditorInput createInput(IModelIdentifier modelIdentifer) {
    final IEditorInput editorInput = EasyMock.createMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(modelIdentifer);
    EasyMock.replay(editorInput);
    return editorInput;
  }

  private ModelIdentifierProvider modelIdentifierProvider;

  @Before
  public void createIdentifierProvider() throws Exception {
    this.modelIdentifierProvider = new ModelIdentifierProvider();
  }

  @Test
  public void returnsNullForNullProvider() throws Exception {
    assertNull(modelIdentifierProvider.getModelIdentifier(null));
  }

  @Test
  public void returnsNullForNonCharacterEditorInput() throws Exception {
    IEditorInput input = createInput(null);
    assertNull(modelIdentifierProvider.getModelIdentifier(input));
  }

  @Test
  public void testname() throws Exception {
    IModelIdentifier modelIdentifier = EasyMock.createMock(IModelIdentifier.class);
    IEditorInput input = createInput(modelIdentifier);
    assertSame(modelIdentifier, modelIdentifierProvider.getModelIdentifier(input));
  }
}