package net.sf.anathema.character.trait.validator.where;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.anathema.character.core.model.content.IContentChecker;
import net.sf.anathema.character.core.model.content.IModelContentCheck;

import org.junit.Before;
import org.junit.Test;

public class WhereModelContent_Test {

  private IWhere where;
  private IContentChecker mockContentChecker;

  @Before
  public void createWhereTraitId() throws Exception {
    mockContentChecker = createMock(IContentChecker.class);
    this.where = new WhereModelContent("definition", mockContentChecker);
  }
  
  @Test
  public void evaluatesFalseIfNoModelCheckIsFoundForDefinition() throws Exception {
    expect(mockContentChecker.getCheck("definition")).andReturn(null);
    replay(mockContentChecker);
    assertFalse(where.evaluate(null, null, null, null));
  }
  
  @Test
  public void evaluatesToFalseIfCheckEvaluatesToFalseForContent() throws Exception {
    IModelContentCheck modelCheck = createContentCheck(false);
    expect(mockContentChecker.getCheck("definition")).andReturn(modelCheck);
    expect(mockContentChecker.getContentValue("definition")).andReturn("content");
    replay(mockContentChecker);
    assertFalse(where.evaluate(null, null, null, null));
  }
  
  @Test
  public void evaluatesToTrueIfCheckEvaluatesToTrueForContent() throws Exception {
    IModelContentCheck modelCheck = createContentCheck(true);
    expect(mockContentChecker.getCheck("definition")).andReturn(modelCheck);
    expect(mockContentChecker.getContentValue("definition")).andReturn("content");
    replay(mockContentChecker);
    assertTrue(where.evaluate(null, null, null, null));
  }

  private IModelContentCheck createContentCheck(boolean evaluation) {
    IModelContentCheck modelCheck = createMock(IModelContentCheck.class);
    expect(modelCheck.evaluate(null, "content")).andReturn(evaluation);
    replay(modelCheck);
    return modelCheck;
  }
}