package net.sf.anathema.character.spiritualtraits.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SpiritualTraitGroupTemplate_Test {

  private SpiritualTraitGroupTemplate template;

  @Before
  public void createTemplate() throws Exception {
    this.template = new SpiritualTraitGroupTemplate();
  }

  @Test
  public void hasOneGroup() throws Exception {
    assertThat(template.getGroups().length, is(1));
  }
}