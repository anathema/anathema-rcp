package net.sf.anathema.character.core.editors;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.IModelIdentifier;

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

  @Test(expected = NullPointerException.class)
  public void doesNotAcceptNullInput() throws Exception {
    modelIdentifierProvider.getModelIdentifier(null);
  }

  @Test
  public void returnsNullForNonCharacterEditorInput() throws Exception {
    IEditorInput input = createInput(null);
    assertNull(modelIdentifierProvider.getModelIdentifier(input));
  }

  @Test
  public void createsModelIdentifierForCharacterEditorInput() throws Exception {
    IModelIdentifier modelIdentifier = EasyMock.createMock(IModelIdentifier.class);
    IEditorInput input = createInput(modelIdentifier);
    assertSame(modelIdentifier, modelIdentifierProvider.getModelIdentifier(input));
  }
}