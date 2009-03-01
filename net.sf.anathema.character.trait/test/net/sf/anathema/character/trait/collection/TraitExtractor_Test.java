package net.sf.anathema.character.trait.collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class TraitExtractor_Test {

  private TraitExtractor traitExtractor;
  private IBasicTrait horst;
  private IBasicTrait martin;

  @Before
  public void createExtractor() throws Exception {
    horst = new BasicTrait("Horst");
    martin = new BasicTrait("Martin");
    final TraitCollection traitCollection = new TraitCollection(horst, martin);
    traitExtractor = new TraitExtractor(traitCollection);
  }

  @Test
  public void extractsTraitWithId() throws Exception {
    assertThat(traitExtractor.transform("Horst"), is(horst));
  }

  @Test
  public void extractsTraitWithOtherId() throws Exception {
    assertThat(traitExtractor.transform("Martin"), is(martin));
  }
}