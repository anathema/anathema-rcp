package net.sf.anathema.editor.styledtext;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.item.text.TitledText;

import org.eclipse.ui.IEditorSite;
import org.junit.Before;
import org.junit.Test;

public class StyledTextEditorTest {

  private ITitledText titledText;
  private StyledTextEditor editor;

  @Before
  public void createEditorInitialedWithTextInput() throws Exception {
    editor = new StyledTextEditor();
    IEditorSite site = createMock(IEditorSite.class);
    replay(site);
    titledText = new TitledText();
    editor.init(site, PersistableEditorInputObjectMother.create(titledText, "Name")); //$NON-NLS-1$
  }

  @Test
  public void hasRegisteredUpdateNameListener() throws Exception {
    assertEquals(1, titledText.getName().getTextChangeListenerCount());
  }

  @Test
  public void removesUpdateNameListenerOnRemove() throws Exception {
    editor.dispose();
    assertEquals(0, titledText.getName().getTextChangeListenerCount());
  }
}