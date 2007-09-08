package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Test;

public class DotsTest {

  @Test
  public void emptyDotsDoNotSpentTotal() throws Exception {
    assertEquals(0, new Dots().spentTotally());
  }
  
  @Test
  public void emptyDotsDoNotSpentOnFavored() throws Exception {
    assertEquals(0, new Dots().spentOnFavored());
  }
  
  @Test
  public void totalIsSumOfAllAttributeCreationValues() throws Exception {
    Dots dots = new Dots(createTrait("Has", 2, false), createTrait("Maus", 3, true));  //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(3, dots.spentTotally());
  }

  @Test
  public void favoredIsSumOfAllFavoredAttributeCreationValues() throws Exception {
    Dots dots = new Dots(createTrait("Has", 2, false), createTrait("Maus", 3, true));  //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(2, dots.spentOnFavored());
  }
  
  @Test
  public void withNoCreditReturnsAllFavoredDotsForExcessOfCredit() throws Exception {
    Dots dots = new Dots(createTrait("Has", 2, false), createTrait("Maus", 3, true));  //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(2, dots.spentOnFavoredInExcessOfCredit(0));
  }
  
  @Test
  public void reducesFavoredDotsByCreditForExcessOfCredit() throws Exception {
    Dots dots = new Dots(createTrait("Has", 2, false), createTrait("Maus", 3, true));  //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(1, dots.spentOnFavoredInExcessOfCredit(2));
  }

  
  @Test
  public void noDotsSpentInExcessOfHighCredit() throws Exception {
    Dots dots = new Dots(createTrait("Has", 2, false), createTrait("Maus", 3, true));  //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(0, dots.spentOnFavoredInExcessOfCredit(4));
  }

  private static BasicTrait createTrait(String id, int value, boolean favored) {
    BasicTrait basicTrait = new BasicTrait(new Identificate(id));
    basicTrait.getCreationModel().setValue(value);
    basicTrait.getFavoredModel().setValue(favored);
    return basicTrait;
  }
}