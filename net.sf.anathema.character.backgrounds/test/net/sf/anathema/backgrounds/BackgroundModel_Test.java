package net.sf.anathema.backgrounds;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.backgrounds.BackgroundModel;
import net.sf.anathema.character.trait.IBasicTrait;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class BackgroundModel_Test {

  private BackgroundModel backgroundModel;

  @Before
  public void createBackgroundModel() throws Exception {
    backgroundModel = new BackgroundModel();
  }

  @Test
  public void containsNoBackgroundsAfterCreation() throws Exception {
    assertThat(backgroundModel.getAllTraits().iterator().hasNext(), is(false));
  }

  @Test
  public void remembersAddedBackgrounds() throws Exception {
    backgroundModel.addBackground("MyBG");
    Iterator<IBasicTrait> allTraits = backgroundModel.getAllTraits().iterator();
    assertThat(allTraits.next().getTraitType().getId(), is("MyBG"));
    assertThat(allTraits.hasNext(), is(false));

  }

  @Test
  public void notifiesListenersOnNewBackground() throws Exception {
    IChangeListener listener = EasyMock.createMock(IChangeListener.class);
    listener.stateChanged();
    EasyMock.replay(listener);
    backgroundModel.addChangeListener(listener);
    backgroundModel.addBackground("Test");
    EasyMock.verify(listener);
  }
}