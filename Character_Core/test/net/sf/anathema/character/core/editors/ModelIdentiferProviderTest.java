package net.sf.anathema.character.core.editors;

import static org.junit.Assert.*;
import net.sf.anathema.basics.item.editor.IEditorInputProvider;
import net.sf.anathema.character.core.model.IModelIdentifier;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class ModelIdentiferProviderTest {

  private static final class StaticEditorInputProvider implements IEditorInputProvider {
    private final IEditorInput editorInput;

    private StaticEditorInputProvider(IEditorInput editorInput) {
      this.editorInput = editorInput;
    }

    @Override
    public IEditorInput getEditorInput() {
      return editorInput;
    }
  }

  private IEditorInputProvider createInputProvider(IModelIdentifier modelIdentifer) {
    final IEditorInput editorInput = EasyMock.createMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(modelIdentifer);
    EasyMock.replay(editorInput);
    return new StaticEditorInputProvider(editorInput);
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
    IEditorInputProvider inputProvider = createInputProvider(null);
    assertNull(modelIdentifierProvider.getModelIdentifier(inputProvider));
  }
  
  @Test
  public void testname() throws Exception {
    IModelIdentifier modelIdentifier = EasyMock.createMock(IModelIdentifier.class);
    IEditorInputProvider inputProvider = createInputProvider(modelIdentifier);
    assertSame(modelIdentifier, modelIdentifierProvider.getModelIdentifier(inputProvider));
  }
}