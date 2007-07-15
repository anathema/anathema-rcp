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
  private DummyCharacterId characterId;
  private ModelIdentifier modelIdentifier;
  private IEditorInput editorInput;
  private IEditorInputProvider inputProvider;

  @Before
  public void createViewInputFactory() throws Exception {
    this.viewInputFactory = new PointViewInputFactory();
  }

  @Before
  public void createCharacterAndEditorInput() {
    this.characterId = new DummyCharacterId();
    characterId.addContentHandle(
        "template.xml", new DummyContentHandle("<template reference=\"net.sf.anathema.core.StaticTemplate\" />")); //$NON-NLS-1$//$NON-NLS-2$
    characterId.addContentHandle("experience.model", new DummyContentHandle("<model experienced=\"false\"/>")); //$NON-NLS-1$ //$NON-NLS-2$
    this.modelIdentifier = new ModelIdentifier(characterId, "Hasän.egal.id"); //$NON-NLS-1$
    this.editorInput = EasyMock.createStrictMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(modelIdentifier).anyTimes();
    this.inputProvider = EasyMock.createStrictMock(IEditorInputProvider.class);
    EasyMock.expect(inputProvider.getEditorInput()).andReturn(editorInput).anyTimes();
  }

  @Test
  public void createsEntriesForCharacter() throws Exception {
    EasyMock.replay(inputProvider, editorInput);
    IPointViewInput newInput = viewInputFactory.createEditorInput(inputProvider);
    assertNotNull(newInput);
    assertNotNull(newInput.getCharacterId());
    assertTrue(newInput.createEntries().length != 0);
    EasyMock.verify(inputProvider, editorInput);
  }

  @Test
  public void createsNoNewInputForIdenticalCharacter() throws Exception {
    EasyMock.replay(inputProvider, editorInput);
    IPointViewInput oldInput = viewInputFactory.createEditorInput(inputProvider);
    IPointViewInput newInput = viewInputFactory.createEditorInput(inputProvider);
    assertEquals(oldInput, newInput);
  }

  @Test
  public void createsNewInputForIdenticalCharacterIfExperienceStateChanged() throws Exception {
    EasyMock.replay(inputProvider, editorInput);
    IPointViewInput oldInput = viewInputFactory.createEditorInput(inputProvider);
    setExperienced();
    IPointViewInput newInput = viewInputFactory.createEditorInput(inputProvider);
    assertFalse(oldInput.equals(newInput));
  }

  @Test
  public void createsNoNewInputForIdenticalCharacterIfExperienceStateDidNotChangeBetweenCalls() throws Exception {
    EasyMock.replay(inputProvider, editorInput);
    setExperienced();
    IPointViewInput oldInput = viewInputFactory.createEditorInput(inputProvider);
    IPointViewInput newInput = viewInputFactory.createEditorInput(inputProvider);
    assertEquals(oldInput, newInput);
  }

  private void setExperienced() {
    IExperience experience = (IExperience) ModelCache.getInstance().getModel(
        new ModelIdentifier(characterId, IExperience.MODEL_ID));
    experience.setExperienced(true);
  }
}