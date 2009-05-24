package net.sf.anathema.character.core.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractModel_Test {

  public static class TestModel extends AbstractModel {
    @Override
    protected void loadFromFromSaveState(Object saveState) {
      // nothing to do;
    }

    @Override
    public Object getSaveState() {
      return null;
    }

    @Override
    public void fireChangedEvent() {
      super.fireChangedEvent();
    }
  }

  @Test
  public void isDirtyAfterChangeEvent() throws Exception {
    TestModel testModel = new TestModel();
    testModel.setDirty(false);
    testModel.fireChangedEvent();
    assertThat(testModel.isDirty(), is(true));
  }

  @Test
  public void isCleanAfterRevert() throws Exception {
    TestModel testModel = new TestModel();
    testModel.setDirty(true);
    testModel.revertTo(null);
    assertThat(testModel.isDirty(), is(false));
  }
}