package net.sf.anathema.character.spiritualtraits.textreport;

import static net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class VirtuesTextEncoder_Test {

  private VirtuesTextEncoder textEncoder;

  @Before
  public void createEncoder() throws Exception {
    textEncoder = new VirtuesTextEncoder();
  }

  @Test
  public void isTitledInternationalizedEssence() throws Exception {
    Messages.VirtuesTextEncoder_Title = "Virtues Title";
    assertThat(textEncoder.getTitle(), is(Messages.VirtuesTextEncoder_Title));
  }

  @Test
  public void returnsTraitIdForTraitName() throws Exception {
    assertThat(textEncoder.getTraitName(new Identificate(COMPASSION_ID)), is(COMPASSION_ID));
    assertThat(textEncoder.getTraitName(new Identificate(CONVICTION_ID)), is(CONVICTION_ID));
  }

  @Test
  public void hasDisplayGroupFactoryContainingVirtuesGroup() throws Exception {
    final SubTraitGroupFactory factory = (SubTraitGroupFactory) textEncoder.getFactory();
    assertThat(factory.getSubGroupName(), is("Virtues"));
  }
}