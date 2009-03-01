package net.sf.anathema.character.trait.collection;

import static org.junit.Assert.*;

import java.util.Collections;

import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.ITraitStatus;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class TraitCollection_SetStatusTest {

  private ITraitStatus setStatus;
  private ITraitStatus otherStatus;
  private IBasicTrait[] traits;

  @Before
  public void name() throws Exception {
    TraitCollection traitCollection = new TraitCollection(
        new BasicTrait(new Identificate("first")), //$NON-NLS-1$
        new BasicTrait(new Identificate("second")), //$NON-NLS-1$
        new BasicTrait(new Identificate("third"))); //$NON-NLS-1$
    setStatus = EasyMock.createMock(ITraitStatus.class);
    otherStatus = EasyMock.createMock(ITraitStatus.class);
    traitCollection.getAllTraits()[0].getStatusManager().setStatus(setStatus);
    traitCollection.getAllTraits()[1].getStatusManager().setStatus(otherStatus);
    traitCollection.setStatusFor(setStatus, Collections.singletonList(new Identificate("third"))); //$NON-NLS-1$
    traits = traitCollection.getAllTraits();
  }
  
  @Test
  public void changesUncontainedTraitWithSetStatusToDefaultStatus() throws Exception {
    assertTrue(traits[0].getStatusManager().getStatus() instanceof DefaultStatus);
  }

  
  @Test
  public void leavesUncontainedTraitWithOtherStateUnchanged() throws Exception {
    assertSame(otherStatus, traits[1].getStatusManager().getStatus());
  }
  
  @Test
  public void setsContainedTraitToSetStatus() throws Exception {
    assertSame(setStatus, traits[2].getStatusManager().getStatus());
  }
}