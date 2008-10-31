package net.sf.anathema.editor.styledtext;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StyledTextEditorActionBarContributorInitializationTest {

  private StyledTextEditorActionBarContributor contributor;

  @Before
  public void createContributor() {
    contributor = new StyledTextEditorActionBarContributor();
  }

  @Test
  public void addsActionsToToolBar() throws Exception {
    TestToolBarManager manager = new TestToolBarManager();
    contributor.contributeToToolBar(manager);
    assertEquals(3, manager.getActions().size());
  }
}