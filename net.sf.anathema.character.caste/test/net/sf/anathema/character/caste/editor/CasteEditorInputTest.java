package net.sf.anathema.character.caste.editor;

import static org.junit.Assert.*;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.character.experience.DummyExperience;

import org.eclipse.core.resources.IFile;
import org.junit.Test;

public class CasteEditorInputTest {

  @Test
  public void isNotModifiableWhenExperienced() throws Exception {
    DummyExperience dummyExperience = new DummyExperience();
    dummyExperience.setExperienced(true);
    IFile file = ResourceObjectMother.createNonExistingFile();
    assertFalse(new CasteEditorInput(file, null, null, null, dummyExperience).isModifiable());
  }

  @Test
  public void isModifiableWhenNotExperienced() throws Exception {
    DummyExperience dummyExperience = new DummyExperience();
    dummyExperience.setExperienced(false);
    IFile file = ResourceObjectMother.createNonExistingFile();
    assertTrue(new CasteEditorInput(file, null, null, null, dummyExperience).isModifiable());
  }

  @Test
  public void throwsEventOnExperienceChange() throws Exception {
    IFile file = ResourceObjectMother.createNonExistingFile();
    DummyExperience dummyExperience = new DummyExperience();
    dummyExperience.setExperienced(false);
    final CasteEditorInput editor = new CasteEditorInput(file, null, null, null, dummyExperience);
    final boolean[] success = new boolean[] { false };
    editor.addModifiableListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        success[0] = true;
      }
    });
    dummyExperience.setExperienced(true);
    assertTrue(success[0]);
  }
  
  @Test
  public void removesListenersOnRequest() throws Exception {
    IFile file = ResourceObjectMother.createNonExistingFile();
    DummyExperience dummyExperience = new DummyExperience();
    dummyExperience.setExperienced(false);
    final CasteEditorInput editor = new CasteEditorInput(file, null, null, null, dummyExperience);
    final boolean[] success = new boolean[] { false };
    IChangeListener listener = new IChangeListener() {
      @Override
      public void stateChanged() {
        success[0] = true;
      }
    };
    editor.addModifiableListener(listener);
    editor.removeModifiableListener(listener);
    dummyExperience.setExperienced(true);
    assertFalse(success[0]);
  }
}