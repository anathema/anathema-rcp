package net.sf.anathema.character.trait.display;

import static org.junit.Assert.*;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.AbstractIntValueModelTest;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DisplayTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractDisplayTraitTest extends AbstractIntValueModelTest {

  private DummyTraitTemplate traitTemplate;
  private Identificate traitType;
  private BasicTrait basicTrait;
  private IFavorizationHandler favorizationHandler;

  protected abstract IExperience createExperience();

  @Before
  public final void createTrait() {
    IExperience basics = createExperience();
    this.traitTemplate = new DummyTraitTemplate();
    this.traitType = new Identificate("test"); //$NON-NLS-1$
    this.basicTrait = new BasicTrait(traitType);
    favorizationHandler = EasyMock.createNiceMock(IFavorizationHandler.class);
    this.model = new DisplayTrait(basicTrait, basics, favorizationHandler, traitTemplate);
  }

  protected final DisplayTrait getDisplayTrait() {
    return (DisplayTrait) model;
  }

  @Test
  public void respectsMaximalValueFromRules() throws Exception {
    this.traitTemplate.setMaximalValue(6);
    assertEquals(6, getDisplayTrait().getMaximalValue());
  }

  @Test
  public void hasBasicTraitType() throws Exception {
    Assert.assertEquals(traitType, getDisplayTrait().getTraitType());
  }

  @Test
  public void removesValueListenersWhenDisposedOf() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    getDisplayTrait().addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        eventReceived[0] = true;
      }
    });
    getDisplayTrait().dispose();
    assertEquals(0, basicTrait.getListenerCount());
  }

  @Test
  public void removesFavorizationListenersWhenDisposedOf() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    getDisplayTrait().addFavoredChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        eventReceived[0] = true;
      }
    });
    getDisplayTrait().dispose();
    getDisplayTrait().toggleFavored();
    assertFalse(eventReceived[0]);
  }

  @Test
  public void favorizationHandlerIsCalledIfFavorizationChanges() throws Exception {
    favorizationHandler.toogleFavored(traitType);
    EasyMock.replay(favorizationHandler);
    getDisplayTrait().toggleFavored();
    EasyMock.verify(favorizationHandler);
  }
  
  @Test
  public void favorizationListenerIsAddedToBasicTraitFavorizationModel() throws Exception {
    IChangeListener favorizationListener = EasyMock.createMock(IChangeListener.class);
    assertEquals(0, basicTrait.getFavoredModel().getChangeListenerCount());
    getDisplayTrait().addFavoredChangeListener(favorizationListener);
    assertEquals(1, basicTrait.getFavoredModel().getChangeListenerCount());
  }
  
  @Test
  public void favorizationListenerIsRemovedOnDispose() throws Exception {
    IChangeListener favorizationListener = EasyMock.createMock(IChangeListener.class);
    getDisplayTrait().addFavoredChangeListener(favorizationListener);
    getDisplayTrait().dispose();
    assertEquals(0, basicTrait.getFavoredModel().getChangeListenerCount());
  }
}