package net.sf.anathema.character.trait.groupeditor;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.interactive.IInteractiveFavorization;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.easymock.EasyMock;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class EnableUpdateListenerTest {

  @Test
  public void initializesButton() throws Exception {
    IInteractiveTrait trait = EasyMock.createMock(IInteractiveTrait.class);
    IInteractiveFavorization favorization = EasyMock.createMock(IInteractiveFavorization.class);
    EasyMock.expect(trait.getFavorization()).andReturn(favorization);
    EasyMock.expect(favorization.isFavorable()).andReturn(false);
    EasyMock.replay(trait, favorization);
    Button button = new Button(new Shell(), SWT.TOGGLE);
    new EnabledUpdateListener(button, trait);
    assertFalse(button.isEnabled());
    // can't test image set, Image is not an interface
    EasyMock.verify(favorization);
  }
  
  @Test
  public void changesState() throws Exception {
    IInteractiveTrait trait = EasyMock.createMock(IInteractiveTrait.class);
    IInteractiveFavorization favorization = EasyMock.createMock(IInteractiveFavorization.class);
    EasyMock.expect(trait.getFavorization()).andReturn(favorization).times(2);
    EasyMock.expect(favorization.isFavorable()).andReturn(false);
    EasyMock.expect(favorization.isFavorable()).andReturn(true);
    EasyMock.replay(trait, favorization);
    Button button = new Button(new Shell(), SWT.TOGGLE);
    EnabledUpdateListener listener = new EnabledUpdateListener(button, trait);
    listener.stateChanged();
    assertTrue(button.isEnabled());
    // can't test image set, Image is not an interface
    EasyMock.verify(favorization);
  }
}
