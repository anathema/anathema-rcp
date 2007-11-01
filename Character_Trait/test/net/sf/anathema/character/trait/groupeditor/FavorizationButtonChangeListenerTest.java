package net.sf.anathema.character.trait.groupeditor;

import static org.junit.Assert.assertTrue;
import net.sf.anathema.character.trait.interactive.IInteractiveFavorization;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.easymock.EasyMock;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class FavorizationButtonChangeListenerTest {

  @Test
  public void togglesFavored() throws Exception {
    IInteractiveTrait trait = EasyMock.createMock(IInteractiveTrait.class);
    IInteractiveFavorization favorization = EasyMock.createMock(IInteractiveFavorization.class);
    EasyMock.expect(trait.getFavorization()).andReturn(favorization);
    favorization.toggleFavored();
    EasyMock.replay(trait, favorization);
    Button button = new Button(new Shell(), SWT.TOGGLE);
    FavorizationButtonChangeListener listener = new FavorizationButtonChangeListener(button, trait);
    listener.handleEvent(null);
    assertTrue(button.getSelection());
    EasyMock.verify(favorization);
  }
}