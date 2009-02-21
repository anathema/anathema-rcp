package net.sf.anathema.character.spiritualtraits.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class SpiritualTraitCollectionTemplate_Test {

  private static final Identificate ESSENCE_ID = new Identificate("Essence");
  private SpiritualTraitCollectionTemplate template;

  @Before
  public void createTemplate() {
    this.template = new SpiritualTraitCollectionTemplate();
  }

  @Test
  public void hasNoFavoredAllowed() throws Exception {
    assertThat(template.getFavorizationTemplate().getAllowedFavored(), is(0));
  }

  @Test
  public void essenceIsNotRequiredFavored() throws Exception {
    assertThat(template.getFavorizationTemplate().isRequiredFavored(ESSENCE_ID), is(false));
  }

  @Test
  public void hasSpiritualTraitGroupTemplate() throws Exception {
    assertThat(template.getGroupTemplate(), is(instanceOf(SpiritualTraitGroupTemplate.class)));
  }
}