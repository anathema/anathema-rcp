package net.sf.anathema.character.trait.collection;

import static org.junit.Assert.*;

import java.util.Collections;

import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.ITraitStatus;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class TraitCollection_SetStatusTest {

  private static final Identificate CHANGE_TRAIT_TYPE = new Identificate("third");
  private ITraitStatus setStatus;
  private ITraitStatus otherStatus;
  private BasicTrait traitWithSetState;
  private BasicTrait traitWithOtherState;
  private BasicTrait traitWithDefaultState;

  @Before
  public void name() throws Exception {
    traitWithSetState = new BasicTrait(new Identificate("first"));
    traitWithOtherState = new BasicTrait(new Identificate("second"));
    traitWithDefaultState = new BasicTrait(CHANGE_TRAIT_TYPE);
    TraitCollection traitCollection = new TraitCollection(traitWithSetState, traitWithOtherState, traitWithDefaultState);
    setStatus = EasyMock.createMock(ITraitStatus.class);
    otherStatus = EasyMock.createMock(ITraitStatus.class);
    traitWithSetState.getStatusManager().setStatus(setStatus);
    traitWithOtherState.getStatusManager().setStatus(otherStatus);
    traitCollection.setStatusFor(setStatus, Collections.singletonList(CHANGE_TRAIT_TYPE));
  }

  @Test
  public void changesUncontainedTraitWithSetStatusToDefaultStatus() throws Exception {
    assertTrue(traitWithSetState.getStatusManager().getStatus() instanceof DefaultStatus);
  }

  @Test
  public void leavesUncontainedTraitWithOtherStateUnchanged() throws Exception {
    assertSame(otherStatus, traitWithOtherState.getStatusManager().getStatus());
  }

  @Test
  public void setsContainedTraitToSetStatus() throws Exception {
    assertSame(setStatus, traitWithDefaultState.getStatusManager().getStatus());
  }
}