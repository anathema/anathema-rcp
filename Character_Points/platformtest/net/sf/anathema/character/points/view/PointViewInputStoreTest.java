package net.sf.anathema.character.points.view;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.fake.DummyContentHandler;
import net.sf.anathema.character.core.fake.TemplateProviderObjectMother;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.points.configuration.internal.PointConfigurationExtensionPoint;
import net.sf.anathema.character.points.view.ICharacterValueEntryFactory;
import net.sf.anathema.character.points.view.PointViewInputStore;

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
    this.characterId = TemplateProviderObjectMother.createCharacterId("net.sf.anathema.charactertype.heroicmortal"); //$NON-NLS-1$
    characterId.addContentHandle("experience.model", new DummyContentHandler("<model experienced=\"false\"/>")); //$NON-NLS-1$ //$NON-NLS-2$
    this.modelIdentifier = new ModelIdentifier(characterId, "Hasän.egal.id"); //$NON-NLS-1$
    this.editorInput = EasyMock.createStrictMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(modelIdentifier).anyTimes();
  }

  @Test
  public void createsEntriesForCharacter() throws Exception {
    EasyMock.replay(editorInput);
    ICharacterValueEntryFactory newInput = viewInputFactory.getEntriesFactory(editorInput);
    assertNotNull(newInput);
    assertNotNull(newInput.getCharacterId());
    assertTrue(newInput.createEntries().length != 0);
    EasyMock.verify(editorInput);
  }

  @Test
  public void createsNoNewInputForIdenticalCharacter() throws Exception {
    EasyMock.replay(editorInput);
    ICharacterValueEntryFactory oldInput = viewInputFactory.getEntriesFactory(editorInput);
    ICharacterValueEntryFactory newInput = viewInputFactory.getEntriesFactory(editorInput);
    assertEquals(oldInput, newInput);
  }

  @Test
  public void createsNewInputForIdenticalCharacterIfExperienceStateChanged() throws Exception {
    EasyMock.replay(editorInput);
    ICharacterValueEntryFactory oldInput = viewInputFactory.getEntriesFactory(editorInput);
    setExperienced();
    ICharacterValueEntryFactory newInput = viewInputFactory.getEntriesFactory(editorInput);
    assertFalse(oldInput.equals(newInput));
  }

  @Test
  public void createsNoNewInputForIdenticalCharacterIfExperienceStateDidNotChangeBetweenCalls() throws Exception {
    EasyMock.replay(editorInput);
    setExperienced();
    ICharacterValueEntryFactory oldInput = viewInputFactory.getEntriesFactory(editorInput);
    ICharacterValueEntryFactory newInput = viewInputFactory.getEntriesFactory(editorInput);
    assertEquals(oldInput, newInput);
  }

  private void setExperienced() {
    IExperience experience = (IExperience) ModelCache.getInstance().getModel(
        new ModelIdentifier(characterId, IExperience.MODEL_ID));
    experience.setExperienced(true);
  }
}