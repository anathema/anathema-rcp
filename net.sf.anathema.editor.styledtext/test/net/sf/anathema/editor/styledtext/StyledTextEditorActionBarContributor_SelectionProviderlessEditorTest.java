package net.sf.anathema.editor.styledtext;

import static org.junit.Assert.*;

import org.eclipse.jface.action.IAction;
import org.junit.Before;
import org.junit.Test;

public class StyledTextEditorActionBarContributor_SelectionProviderlessEditorTest {

  private StyledTextEditorActionBarContributor contributor;

  @Before
  public void createContributor() throws Exception {
    contributor = new StyledTextEditorActionBarContributor();
    contributor.setActiveEditor(StyledTextEditorObjectMother.createStyledTextEditorWithoutSelectionProvider());
  }

  @Test
  public void contributedActionsAreDisabled() throws Exception {
    TestToolBarManager toolbarManager = new TestToolBarManager();
    contributor.contributeToToolBar(toolbarManager);
    for (IAction action : toolbarManager.getActions()) {
      assertFalse(action.isEnabled());
    }
  }
}