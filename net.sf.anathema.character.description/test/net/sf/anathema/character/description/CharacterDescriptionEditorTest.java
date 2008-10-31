package net.sf.anathema.character.description;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.core.fake.CharacterObjectMother;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorSite;
import org.junit.Test;

public class CharacterDescriptionEditorTest {

  @Test
  public void disposeRemovesTextChangeListener() throws Exception {
    CharacterDescriptionEditor editor = new CharacterDescriptionEditor();
    CharacterDescription description = new CharacterDescription();
    editor.init(
        EasyMock.createMock(IEditorSite.class),
        CharacterObjectMother.createPersistableEditorInputFor(description));
    editor.dispose();
    assertEquals(0, description.getName().getTextChangeListenerCount());
  }
}