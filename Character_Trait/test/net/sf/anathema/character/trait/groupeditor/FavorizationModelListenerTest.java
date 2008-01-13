package net.sf.anathema.character.trait.groupeditor;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.interactive.IInteractiveFavorization;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.easymock.EasyMock;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class FavorizationModelListenerTest {
  public static final class NullImageProvider implements IImageProvider {
    @Override
    public Image getImage() {
      return null;
    }
  }

  @Test
  public void initializesButton() throws Exception {
    IInteractiveTrait trait = EasyMock.createMock(IInteractiveTrait.class);
    IInteractiveFavorization favorization = EasyMock.createMock(IInteractiveFavorization.class);
    EasyMock.expect(trait.getFavorization()).andReturn(favorization);
    EasyMock.expect(favorization.isFavored()).andReturn(false);
    EasyMock.replay(trait, favorization);
    Button button = new Button(new Shell(), SWT.TOGGLE);
    new FavorizationModelListener(button, trait, new NullImageProvider());
    assertFalse(button.getSelection());
    // can't test image set, Image is not an interface
    EasyMock.verify(favorization);
  }

  @Test
  public void changesButtonState() throws Exception {
    IInteractiveTrait trait = EasyMock.createMock(IInteractiveTrait.class);
    IInteractiveFavorization favorization = EasyMock.createMock(IInteractiveFavorization.class);
    EasyMock.expect(trait.getFavorization()).andReturn(favorization).times(2);
    EasyMock.expect(favorization.isFavored()).andReturn(false);
    EasyMock.expect(favorization.isFavored()).andReturn(true);
    EasyMock.replay(trait, favorization);
    Button button = new Button(new Shell(), SWT.TOGGLE);
    FavorizationModelListener listener = new FavorizationModelListener(button, trait, new NullImageProvider());
    listener.stateChanged();
    assertTrue(button.getSelection());
    // can't test image set, Image is not an interface
    EasyMock.verify(favorization);
  }
}