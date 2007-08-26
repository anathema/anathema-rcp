package net.sf.anathema.lib.textualdescription;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.ui.IDisposable;

public class TextualPresenter implements IDisposable {
  
  private final ITextView textView;
  private final ITextualDescription textualDescription;
  private final IObjectValueChangedListener<String> modelListener = new IObjectValueChangedListener<String>() {
    public void valueChanged(String newValue) {
      textView.setText(newValue);
    }
  };
  private final IObjectValueChangedListener<String> viewListener = new IObjectValueChangedListener<String>() {
    public void valueChanged(String newValue) {
      textualDescription.setText(newValue);
    }
  };

  public TextualPresenter(final ITextView textView, final ITextualDescription textualDescription) {
    Ensure.ensureArgumentNotNull(textView);
    Ensure.ensureArgumentNotNull(textualDescription);
    this.textView = textView;
    this.textualDescription = textualDescription;
  }

  public void initPresentation() {
    textView.addTextChangedListener(viewListener);
    textView.setText(textualDescription.getText());
    textualDescription.addTextChangedListener(modelListener);
  }

  @Override
  public void dispose() {
    textView.removeTextChangeListener(viewListener);
    textualDescription.removeTextChangeListener(modelListener);
  }
}