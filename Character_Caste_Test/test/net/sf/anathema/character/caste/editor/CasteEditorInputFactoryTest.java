package net.sf.anathema.character.caste.editor;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.character.caste.model.CasteModel;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.core.fake.DummyModelCollection;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;

import org.eclipse.core.resources.IFile;
import org.junit.Before;
import org.junit.Test;

public class CasteEditorInputFactoryTest {

  private CasteEditorInputFactory factory;
  private DummyModelCollection modelCollection;
  private IFile file;

  @Before
  public void createFactory() {
    this.factory = new CasteEditorInputFactory();
  }

  @Before
  public void createFile() {
    file = ResourceObjectMother.createFile(null);
  }

  @Before
  public void createModelCollection() {
    modelCollection = new DummyModelCollection();
    modelCollection.addModel(IExperience.MODEL_ID, new DummyExperience());
  }

  @Test
  public void createsCasteEditorInput() throws Exception {
    CasteEditorInput editorInput = factory.create(file, null, null, null, modelCollection);
    assertNotNull(editorInput);
  }

  @Test
  public void createdInputHasCorrectItem() throws Exception {
    CasteModel casteModel = new CasteModel(new CasteTemplate());
    modelCollection.addModel(ICasteModel.ID, casteModel);
    CasteEditorInput editorInput = factory.create(file, null, null, null, modelCollection);
    assertSame(casteModel, editorInput.getItem());
  }
}