package net.sf.anathema.character.spiritualtraits.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Locale;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.test.hamcrest.AnathemaMatchers;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class SpiritualTraitGroupTemplate_VirtuesGroupTest {

  private TraitGroup virtuesGroup;

  @Before
  public void createTemplate() throws Exception {
    SpiritualTraitGroupTemplate template = new SpiritualTraitGroupTemplate();
    virtuesGroup = template.getGroups()[1];
  }

  @Test
  public void hasIdEssence() throws Exception {
    assertThat(virtuesGroup.getId(), is("Virtues"));
  }

  @Test
  public void hasEnglishLabelEssence() throws Exception {
    Locale.setDefault(Locale.ENGLISH);
    assertThat(virtuesGroup.getLabel(), is("Virtues"));
  }

  @Test
  public void contains4TraitIds() throws Exception {
    assertThat(virtuesGroup.getTraitIds().length, is(4));
  }

  @Test
  public void containsTraitIdCompassion() throws Exception {
    assertThat(virtuesGroup.getTraitIds(), AnathemaMatchers.contains("Compassion"));
  }

  @Test
  public void containsTraitIdConviction() throws Exception {
    assertThat(virtuesGroup.getTraitIds(), AnathemaMatchers.contains("Conviction"));
  }

  @Test
  public void containsTraitIdTemperance() throws Exception {
    assertThat(virtuesGroup.getTraitIds(), AnathemaMatchers.contains("Temperance"));
  }

  @Test
  public void containsTraitIdValor() throws Exception {
    assertThat(virtuesGroup.getTraitIds(), AnathemaMatchers.contains("Valor"));
  }
}