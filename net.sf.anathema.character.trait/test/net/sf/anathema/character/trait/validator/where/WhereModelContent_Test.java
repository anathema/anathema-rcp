package net.sf.anathema.character.trait.validator.where;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.model.content.IContentChecker;
import net.sf.anathema.character.core.model.content.IModelContentCheck;

import org.junit.Before;
import org.junit.Test;

public class WhereModelContent_Test {

  private static final String CONTENT = "content"; //$NON-NLS-1$
  private static final String DEFINITION = "definition"; //$NON-NLS-1$
  private IWhere where;
  private IContentChecker mockContentChecker;

  @Before
  public void createWhereTraitId() throws Exception {
    mockContentChecker = createMock(IContentChecker.class);
    this.where = new WhereModelContent(DEFINITION, mockContentChecker);
  }

  @Test
  public void evaluatesFalseIfNoModelCheckIsFoundForDefinition() throws Exception {
    expect(mockContentChecker.getCheck(DEFINITION)).andReturn(null);
    replay(mockContentChecker);
    assertFalse(where.evaluate(new ValidationDto()));
  }

  @Test
  public void evaluatesToFalseIfCheckEvaluatesToFalseForContent() throws Exception {
    IModelContentCheck modelCheck = createContentCheck(false);
    expect(mockContentChecker.getCheck(DEFINITION)).andReturn(modelCheck);
    expect(mockContentChecker.getContentValue(DEFINITION)).andReturn(CONTENT);
    replay(mockContentChecker);
    assertFalse(where.evaluate(new ValidationDto()));
  }

  @Test
  public void evaluatesToTrueIfCheckEvaluatesToTrueForContent() throws Exception {
    IModelContentCheck modelCheck = createContentCheck(true);
    expect(mockContentChecker.getCheck(DEFINITION)).andReturn(modelCheck);
    expect(mockContentChecker.getContentValue(DEFINITION)).andReturn(CONTENT);
    replay(mockContentChecker);
    assertTrue(where.evaluate(new ValidationDto()));
  }

  private IModelContentCheck createContentCheck(boolean evaluation) {
    IModelContentCheck modelCheck = createMock(IModelContentCheck.class);
    expect(modelCheck.evaluate(null, CONTENT)).andReturn(evaluation);
    replay(modelCheck);
    return modelCheck;
  }
}