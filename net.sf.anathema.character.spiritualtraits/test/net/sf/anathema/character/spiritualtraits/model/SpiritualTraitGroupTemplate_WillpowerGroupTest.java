package net.sf.anathema.character.spiritualtraits.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Locale;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.test.hamcrest.AnathemaMatchers;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class SpiritualTraitGroupTemplate_WillpowerGroupTest {

  private TraitGroup willpowerGroup;

  @Before
  public void createTemplate() throws Exception {
    SpiritualTraitGroupTemplate template = new SpiritualTraitGroupTemplate();
    willpowerGroup = template.getGroups()[2];
  }

  @Test
  public void hasIdEssence() throws Exception {
    assertThat(willpowerGroup.getId(), is("Willpower"));
  }

  @Test
  public void hasEnglishLabelEssence() throws Exception {
    Locale.setDefault(Locale.ENGLISH);
    assertThat(willpowerGroup.getLabel(), is("Willpower"));
  }

  @Test
  public void containsOneTraitIds() throws Exception {
    assertThat(willpowerGroup.getTraitIds().length, is(1));
  }

  @Test
  public void containsTraitIdEssence() throws Exception {
    assertThat(willpowerGroup.getTraitIds(), AnathemaMatchers.contains("Willpower"));
  }
}