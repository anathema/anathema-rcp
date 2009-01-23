package net.sf.anathema.charms.character.sheet.generic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CategoryNames_Test {

  @Test
  public void returnsAttributesMessageForAttributeId() throws Exception {
    String expectedString = "expectedString";
    Messages.CategoryNames_Attribute = expectedString;
    assertThat(
        new CategoryNames().getName("net.sf.anathema.character.attributes.model.forgenerics"),
        is(expectedString));
  }

  @Test
  public void returnsAttributesMessageForAbilitiesId() throws Exception {
    String expectedString = "expectedString";
    Messages.CategoryNames_Ability = expectedString;
    assertThat(new CategoryNames().getName("net.sf.anathema.character.abilities.model.forgenerics"), is(expectedString));
  }

  @Test
  public void returnsIdentityForUnknownId() throws Exception {
    String id = "id";
    assertThat(new CategoryNames().getName(id), is(id));
  }
}