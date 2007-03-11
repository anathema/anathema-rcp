package net.sf.anathema.lib.textualdescription;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.lib.collection.IClosure;
import net.sf.anathema.lib.control.GenericControl;

import org.easymock.EasyMock;
import org.junit.Test;

public class StyledTextPresenterTest {

  private static class DummyStyledTextView implements IStyledTextView {

    public final GenericControl<ITextExchangeListener> exchangeListeners = new GenericControl<ITextExchangeListener>();
    private String newText;

    @Override
    public void setFocus() {
      // nothing to do
    }

    @Override
    public void setContent(final String newText, ITextPart[] parts) {
      this.newText = newText;
      exchangeListeners.forAllDo(new IClosure<ITextExchangeListener>() {
        @Override
        public void execute(ITextExchangeListener input) {
          input.textReplaced(0, 0, newText);
        }
      });
    }

    @Override
    public void addTextExchangeListener(ITextExchangeListener listener) {
      exchangeListeners.addListener(listener);
    }
  }

  @Test
  public void noListenerNotificationForViewInitialization() throws Exception {
    DummyStyledTextView view = new DummyStyledTextView();
    IStyledTextualDescription description = EasyMock.createNiceMock(IStyledTextualDescription.class);
    EasyMock.expect(description.getText()).andReturn("Hasä"); //$NON-NLS-1$
    EasyMock.expect(description.getTextParts()).andReturn(new ITextPart[0]);
    description.addTextChangedListener((IStyledTextChangeListener) EasyMock.notNull());
    EasyMock.replay(description);
    description.setText("Hasä"); //$NON-NLS-1$
    new StyledTextPresenter(view, description).initPresentation();
    assertEquals("Hasä", view.newText); //$NON-NLS-1$
    EasyMock.verify(description);
  }
}