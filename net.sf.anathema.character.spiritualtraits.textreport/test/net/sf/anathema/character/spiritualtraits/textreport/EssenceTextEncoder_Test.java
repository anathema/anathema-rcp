package net.sf.anathema.character.spiritualtraits.textreport;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class EssenceTextEncoder_Test {

  private EssenceTextEncoder textEncoder;

  @Before
  public void createEncoder() throws Exception {
    textEncoder = new EssenceTextEncoder();
  }

  @Test
  public void isTitledInternationalizedEssence() throws Exception {
    Messages.EssenceTextEncoder_Title = "Test";
    assertThat(textEncoder.getTitle(), is(Messages.EssenceTextEncoder_Title));
  }

  @Test
  public void returnsNothingForTraitName() throws Exception {
    assertThat(textEncoder.getTraitName(new Identificate("Essence")), is(""));
  }

  @Test
  public void hasDisplayGroupFactoryContainingEssence() throws Exception {
    final SubTraitGroupFactory factory = (SubTraitGroupFactory) textEncoder.getFactory();
    assertThat(factory.getSubGroupName(), is("Essence"));
  }
}