package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;
import net.sf.anathema.character.freebies.attributes.calculation.Dots;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Test;

public class DotsTest {

  @Test
  public void emptyDotsDoNotSpentTotal() throws Exception {
    assertEquals(0, new Dots(12).spentTotally());
  }

  @Test
  public void emptyDotsDoNotSpentOnFavored() throws Exception {
    assertEquals(0, new Dots(12).spentOnCheap());
  }

  @Test
  public void totalIsSumOfAllAttributeCreationValues() throws Exception {
    Dots dots = new Dots(12, createTrait("Has", 2, false), createTrait("Maus", 3, true)); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(3, dots.spentTotally());
  }

  @Test
  public void favoredIsSumOfAllFavoredAttributeCreationValues() throws Exception {
    Dots dots = new Dots(12, createTrait("Has", 2, false), createTrait("Maus", 3, true)); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(2, dots.spentOnCheap());
  }

  @Test
  public void withNoCreditReturnsAllFavoredDotsForExcessOfCredit() throws Exception {
    Dots dots = new Dots(0, createTrait("Has", 2, false), createTrait("Maus", 3, true)); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(2, dots.spentOnCheapInExcessOfCredit());
  }

  @Test
  public void reducesFavoredDotsByCreditForExcessOfCredit() throws Exception {
    Dots dots = new Dots(2, createTrait("Has", 2, false), createTrait("Maus", 3, true)); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(1, dots.spentOnCheapInExcessOfCredit());
  }

  @Test
  public void noDotsSpentInExcessOfHighCredit() throws Exception {
    Dots dots = new Dots(4, createTrait("Has", 2, false), createTrait("Maus", 3, true)); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(0, dots.spentOnCheapInExcessOfCredit());
  }

  @Test
  public void allFavoredDotsPartOfCreditForHighCredit() throws Exception {
    Dots dots = new Dots(6, createTrait("Has", 2, false), createTrait("Maus", 3, true)); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(2, dots.cheaplySpentAsPartOfCredit());
  }

  @Test
  public void oneFavoredDotPartOfCreditIfCreditInsufficient() throws Exception {
    Dots dots = new Dots(2, createTrait("Has", 2, false), createTrait("Maus", 3, true)); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(1, dots.cheaplySpentAsPartOfCredit());
  }

  @Test
  public void allUnfavoredDotsPartOfCreditForHighCredit() throws Exception {
    Dots dots = new Dots(6, createTrait("Has", 3, false), createTrait("Maus", 3, true)); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(2, dots.expensivlySpentAsPartOfCredit());
  }

  @Test
  public void oneUnfavoredDotPartOfCreditIfCreditInsufficient() throws Exception {
    Dots dots = new Dots(1, createTrait("Has", 3, false), createTrait("Maus", 3, true)); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals(1, dots.expensivlySpentAsPartOfCredit());
  }

  private static BasicTrait createTrait(String id, int value, boolean favored) {
    BasicTrait basicTrait = new BasicTrait(new Identificate(id));
    basicTrait.getCreationModel().setValue(value);
    if (favored) {
      basicTrait.getStatusManager().setStatus(new FavoredStatus());
    }
    return basicTrait;
  }
}