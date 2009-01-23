package net.sf.anathema.charms.view;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SugiyamaLayoutAlgorithm_Test {

  private SugiyamaLayoutAlgorithm algorithm;

  @Before
  public void createAlgorithm() throws Exception {
    algorithm = new SugiyamaLayoutAlgorithm();
  }

  @Test(expected = RuntimeException.class)
  public void throwsExceptionWhenSettingArea() throws Exception {
    algorithm.setLayoutArea(0, 0, 0, 0);
  }

  @Test
  public void hasSingleLayoutStep() throws Exception {
    assertThat(algorithm.getTotalNumberOfLayoutSteps(), is(1));
  }

  @Test
  public void startsWithLayoutStepZero() throws Exception {
    assertThat(algorithm.getCurrentLayoutStep(), is(0));
  }

  @Test
  public void isNotValidIfContinueous() throws Exception {
    assertThat(algorithm.isValidConfiguration(true, true), is(false));
    assertThat(algorithm.isValidConfiguration(false, true), is(false));
  }

  @Test
  public void isValidIfNotContinueous() throws Exception {
    assertThat(algorithm.isValidConfiguration(true, false), is(true));
    assertThat(algorithm.isValidConfiguration(false, false), is(true));
  }
}