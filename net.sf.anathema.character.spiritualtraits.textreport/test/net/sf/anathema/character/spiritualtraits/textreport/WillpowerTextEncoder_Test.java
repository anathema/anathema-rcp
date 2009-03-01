package net.sf.anathema.character.spiritualtraits.textreport;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.spiritualtraits.model.SubTraitGroupFactory;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class WillpowerTextEncoder_Test {

  private WillpowerTextEncoder textEncoder;

  @Before
  public void createEncoder() throws Exception {
    textEncoder = new WillpowerTextEncoder();
  }

  @Test
  public void isTitledInternationalizedEssence() throws Exception {
    Messages.WillpowerTextEncoder_Title = "Willpower Title";
    assertThat(textEncoder.getTitle(), is(Messages.WillpowerTextEncoder_Title));
  }

  @Test
  public void returnsNothingForTraitName() throws Exception {
    assertThat(textEncoder.getTraitName(new Identificate("Willpower")), is(""));
  }

  @Test
  public void hasDisplayGroupFactoryContainingWillpowerGroup() throws Exception {
    final SubTraitGroupFactory factory = (SubTraitGroupFactory) textEncoder.getFactory();
    assertThat(factory.getSubGroupName(), is("Willpower"));
  }
}