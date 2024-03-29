package net.sf.anathema.character.spiritualtraits.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Locale;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.test.hamcrest.AnathemaMatchers;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class SpiritualTraitGroupTemplate_EssenceGroupTest {

  private TraitGroup essenceGroup;
  private SpiritualTraitGroupTemplate template;

  @Before
  public void createTemplate() throws Exception {
    template = new SpiritualTraitGroupTemplate();
    essenceGroup = template.getGroups()[0];
  }

  @Test
  public void hasTwoGroup() throws Exception {
    assertThat(template.getGroups().length, is(3));
  }

  @Test
  public void hasIdEssence() throws Exception {
    assertThat(essenceGroup.getId(), is("Essence"));
  }

  @Test
  public void hasEnglishLabelEssence() throws Exception {
    Locale.setDefault(Locale.ENGLISH);
    assertThat(essenceGroup.getLabel(), is("Essence"));
  }

  @Test
  public void containsOneTraitIds() throws Exception {
    assertThat(essenceGroup.getTraitIds().length, is(1));
  }

  @Test
  public void containsTraitIdEssence() throws Exception {
    assertThat(essenceGroup.getTraitIds(), AnathemaMatchers.contains("Essence"));
  }
}