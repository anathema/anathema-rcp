package net.sf.anathema.character.points;

import static org.junit.Assert.*;
import net.sf.anathema.basics.item.editor.IEditorInputProvider;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class PointViewInputFactoryTest {

  private PointViewInputFactory viewInputFactory;

  private void assertEmptyViewElement(IPointViewInput newInput) {
    assertNotNull(newInput);
    assertNull(newInput.getCharacterId());
    assertArrayEquals(new IPointEntry[0], newInput.createEntries());
  }

  @Before
  public void createViewInputFactory() throws Exception {
    this.viewInputFactory = new PointViewInputFactory();
  }

  @Test
  public void nullInputProviderReturnsEmptyViewInput() throws Exception {
    IPointViewInput newInput = viewInputFactory.createEditorInput(null);
    assertEmptyViewElement(newInput);
  }

  @Test
  public void nonCharacterEditorInputReturnsEmptyViewInput() throws Exception {
    IEditorInput editorInput = EasyMock.createStrictMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(null);
    IEditorInputProvider inputProvider = EasyMock.createStrictMock(IEditorInputProvider.class);
    EasyMock.expect(inputProvider.getEditorInput()).andReturn(editorInput);
    EasyMock.replay(inputProvider, editorInput);
    IPointViewInput newInput = viewInputFactory.createEditorInput(inputProvider);
    assertEmptyViewElement(newInput);
    EasyMock.verify(inputProvider, editorInput);
  }

  @Test
  public void createsEntriesForCharacterEditorInput() throws Exception {
    DummyCharacterId characterId = new DummyCharacterId();
    addTemplateHandle(characterId);
    IEditorInput editorInput = EasyMock.createStrictMock(IEditorInput.class);
    ModelIdentifier egalIdentifier = new ModelIdentifier(characterId, "Hasän.egal.id"); //$NON-NLS-1$
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(egalIdentifier);
    IEditorInputProvider inputProvider = EasyMock.createStrictMock(IEditorInputProvider.class);
    EasyMock.expect(inputProvider.getEditorInput()).andReturn(editorInput);
    EasyMock.replay(inputProvider, editorInput);
    IPointViewInput newInput = viewInputFactory.createEditorInput(inputProvider);
    assertNotNull(newInput);
    assertNotNull(newInput.getCharacterId());
    assertTrue(newInput.createEntries().length != 0);
    EasyMock.verify(inputProvider, editorInput);
  }

  @Test
  public void createsNoNewInputForIdenticalCharacterEditorInput() throws Exception {
    DummyCharacterId characterId = new DummyCharacterId();
    addTemplateHandle(characterId);
    IEditorInput editorInput = EasyMock.createStrictMock(IEditorInput.class);
    ModelIdentifier egalIdentifier = new ModelIdentifier(characterId, "Hasän.egal.id"); //$NON-NLS-1$
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(egalIdentifier).anyTimes();
    IEditorInputProvider inputProvider = EasyMock.createStrictMock(IEditorInputProvider.class);
    EasyMock.expect(inputProvider.getEditorInput()).andReturn(editorInput).anyTimes();
    EasyMock.replay(inputProvider, editorInput);
    IPointViewInput oldInput = viewInputFactory.createEditorInput(inputProvider);
    IPointViewInput newInput = viewInputFactory.createEditorInput(inputProvider);
    assertEquals(oldInput, newInput);
  }

  @Test
  public void createsNewInputForIdenticalCharacterEditorInputIfExperienceStateChanged() throws Exception {
    DummyCharacterId characterId = new DummyCharacterId();
    addTemplateHandle(characterId);
    addExperienceHandle(characterId);
    IEditorInput editorInput = EasyMock.createStrictMock(IEditorInput.class);
    ModelIdentifier egalIdentifier = new ModelIdentifier(characterId, "Hasän.egal.id"); //$NON-NLS-1$
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(egalIdentifier).anyTimes();
    IEditorInputProvider inputProvider = EasyMock.createStrictMock(IEditorInputProvider.class);
    EasyMock.expect(inputProvider.getEditorInput()).andReturn(editorInput).anyTimes();
    EasyMock.replay(inputProvider, editorInput);
    IPointViewInput oldInput = viewInputFactory.createEditorInput(inputProvider);
    IExperience experience = (IExperience) ModelCache.getInstance().getModel(
        new ModelIdentifier(characterId, IExperience.MODEL_ID));
    experience.setExperienced(true);
    IPointViewInput newInput = viewInputFactory.createEditorInput(inputProvider);
    assertFalse(oldInput.equals(newInput));
  }

  private void addTemplateHandle(DummyCharacterId characterId) {
    characterId.addContentHandle(
        "template.xml", new DummyContentHandle("<template reference=\"net.sf.anathema.core.StaticTemplate\" />")); //$NON-NLS-1$//$NON-NLS-2$
  }

  private void addExperienceHandle(DummyCharacterId characterId) {
    characterId.addContentHandle("experience.model", new DummyContentHandle("<model experienced=\"false\"/>")); //$NON-NLS-1$
  }
}