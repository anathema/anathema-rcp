package net.sf.anathema.basics.repository.problems;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class ProblemsLabelProviderTest {

  private static final String PATH = "PathItIs"; //$NON-NLS-1$
  private static final String DESCRIPTION = "myNiceDescription"; //$NON-NLS-1$
  private IProblem problem;
  private ProblemsLabelProvider labelProvider;

  @Before
  public void createProblem() throws Exception {
    problem = EasyMock.createMock(IProblem.class);
    EasyMock.expect(problem.getDescription()).andReturn(DESCRIPTION).anyTimes();
    EasyMock.expect(problem.getPath()).andReturn(PATH).anyTimes();
    EasyMock.replay(problem);
  }

  @Before
  public void createLabelProvder() throws Exception {
    labelProvider = new ProblemsLabelProvider();
  }

  @Test
  public void noIconProvided() throws Exception {
    assertNull(labelProvider.getImage(problem));
  }

  @Test
  public void problemDescriptionIsReturnedForColumnIndex0() throws Exception {
    assertEquals(DESCRIPTION, labelProvider.getColumnText(problem, 0));
  }

  @Test
  public void problemPathIsReturnedForColumnIndex1() throws Exception {
    assertEquals(PATH, labelProvider.getColumnText(problem, 1));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void throwsIndexOutOfBoundExceptionForColumnIndex2() throws Exception {
    labelProvider.getColumnText(problem, 2);
  }
}