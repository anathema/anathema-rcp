package net.sf.anathema.character.abilities.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class AbilitiesMessages_Test {

  private AbilitiesMessages messages;

  @Before
  public void createMessage() throws Exception {
    messages = new AbilitiesMessages();
  }

  @Test
  public void knowsWar() throws Exception {
    assertThat(messages.knowsNameFor("War"), is(true));
  }

  @Test
  public void doesNotKnowStrength() throws Exception {
    assertThat(messages.knowsNameFor("Strength"), is(false));
  }
}