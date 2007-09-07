package net.sf.anathema.character.trait.display;

import static org.junit.Assert.*;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DisplayFavorization;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class DisplayFavorizationTest {

  private Identificate traitType;
  private BasicTrait basicTrait;
  private IFavorizationHandler favorizationHandler;
  private DisplayFavorization favorization;
  private DummyExperience experience;

  @Before
  public final void createTrait() {
    this.experience = new DummyExperience();
    this.traitType = new Identificate("test"); //$NON-NLS-1$
    this.basicTrait = new BasicTrait(traitType);
    this.favorizationHandler = EasyMock.createNiceMock(IFavorizationHandler.class);
    this.favorization = new DisplayFavorization(basicTrait, experience, favorizationHandler);
  }

  @Test
  public void removesFavorizationListenersWhenDisposedOf() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    favorization.addFavoredChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        eventReceived[0] = true;
      }
    });
    favorization.dispose();
    favorization.toggleFavored();
    assertFalse(eventReceived[0]);
  }

  @Test
  public void favorizationHandlerIsCalledIfFavorizationChanges() throws Exception {
    favorizationHandler.toogleFavored(traitType);
    EasyMock.replay(favorizationHandler);
    favorization.toggleFavored();
    EasyMock.verify(favorizationHandler);
  }
  
  @Test
  public void favorizationListenerIsAddedToBasicTraitFavorizationModel() throws Exception {
    IChangeListener favorizationListener = EasyMock.createMock(IChangeListener.class);
    assertEquals(0, basicTrait.getFavoredModel().getChangeListenerCount());
    favorization.addFavoredChangeListener(favorizationListener);
    assertEquals(1, basicTrait.getFavoredModel().getChangeListenerCount());
  }
  
  @Test
  public void favorizationListenerIsRemovedOnDispose() throws Exception {
    IChangeListener favorizationListener = EasyMock.createMock(IChangeListener.class);
    favorization.addFavoredChangeListener(favorizationListener);
    assertEquals(1, basicTrait.getFavoredModel().getChangeListenerCount());
    favorization.dispose();
    assertEquals(0, basicTrait.getFavoredModel().getChangeListenerCount());
  }
 
  @Test
  public void favorizableListenerIsRemovedOnDispose() throws Exception {
    IChangeListener favorizableListener = EasyMock.createMock(IChangeListener.class);
    favorization.addFavorableChangeListener(favorizableListener);
    assertEquals(1, experience.getListenerCount());
    favorization.dispose();
    assertEquals(0, experience.getListenerCount());
  }
  
  @Test
  public void experienceOverrulesFavorablityToFalse() throws Exception {
    experience.setExperienced(true);
    EasyMock.expect(favorizationHandler.isFavorable()).andReturn(true).anyTimes();
    EasyMock.replay(favorizationHandler);
    assertFalse(favorization.isFavorable());
  }
  
  @Test
  public void favorablilityIsDecidedByFavorizationHandlerUnexperiencedCharacter() throws Exception {
    experience.setExperienced(false);
    EasyMock.expect(favorizationHandler.isFavorable()).andReturn(false);
    EasyMock.expect(favorizationHandler.isFavorable()).andReturn(true);
    EasyMock.replay(favorizationHandler);
    assertFalse(favorization.isFavorable());
    assertTrue(favorization.isFavorable());
  }
  
  @Test
  public void favorabiltyListenerIsNotifiedOnExperienceChange() throws Exception {
    IChangeListener favorableListener = EasyMock.createMock(IChangeListener.class);
    favorableListener.stateChanged();
    EasyMock.replay(favorableListener);
    favorization.addFavorableChangeListener(favorableListener);
    experience.setExperienced(!experience.isExperienced());
    EasyMock.verify(favorableListener);
  }
}