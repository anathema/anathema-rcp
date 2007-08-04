package net.sf.anathema.character.points.model;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.model.IExperience;
import net.sf.anathema.character.points.IPointViewInput;
import net.sf.anathema.character.points.PointConfigurationExtensionPoint;
import net.sf.anathema.character.points.PointViewInputStore;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class PointViewInputStoreTest {

  private PointViewInputStore viewInputFactory;
  private DummyCharacterId characterId;
  private ModelIdentifier modelIdentifier;
  private IEditorInput editorInput;

  @Before
  public void createViewInputFactory() throws Exception {
    this.viewInputFactory = new PointViewInputStore(new PointConfigurationExtensionPoint());
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
  }

  @Test
  public void createsEntriesForCharacter() throws Exception {
    EasyMock.replay(editorInput);
    IPointViewInput newInput = viewInputFactory.getViewInput(editorInput);
    assertNotNull(newInput);
    assertNotNull(newInput.getCharacterId());
    assertTrue(newInput.createEntries().length != 0);
    EasyMock.verify(editorInput);
  }

  @Test
  public void createsNoNewInputForIdenticalCharacter() throws Exception {
    EasyMock.replay(editorInput);
    IPointViewInput oldInput = viewInputFactory.getViewInput(editorInput);
    IPointViewInput newInput = viewInputFactory.getViewInput(editorInput);
    assertEquals(oldInput, newInput);
  }

  @Test
  public void createsNewInputForIdenticalCharacterIfExperienceStateChanged() throws Exception {
    EasyMock.replay(editorInput);
    IPointViewInput oldInput = viewInputFactory.getViewInput(editorInput);
    setExperienced();
    IPointViewInput newInput = viewInputFactory.getViewInput(editorInput);
    assertFalse(oldInput.equals(newInput));
  }

  @Test
  public void createsNoNewInputForIdenticalCharacterIfExperienceStateDidNotChangeBetweenCalls() throws Exception {
    EasyMock.replay(editorInput);
    setExperienced();
    IPointViewInput oldInput = viewInputFactory.getViewInput(editorInput);
    IPointViewInput newInput = viewInputFactory.getViewInput(editorInput);
    assertEquals(oldInput, newInput);
  }

  private void setExperienced() {
    IExperience experience = (IExperience) ModelCache.getInstance().getModel(
        new ModelIdentifier(characterId, IExperience.MODEL_ID));
    experience.setExperienced(true);
  }
}