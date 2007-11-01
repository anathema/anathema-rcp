package net.sf.anathema.character.trait;

import net.sf.anathema.character.trait.interactive.IntValueModel;

import org.junit.Before;

public class IntValueModelTest extends AbstractIntValueModelTest {

  @Before
  public void createModel() {
    this.model = new IntValueModel();
  }
}