package net.sf.anathema.lib.textualdescription;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class TextualPresenter {
  
  private final ITextView textView;
  private final ITextualDescription textualDescription;

  public TextualPresenter(final ITextView textView, final ITextualDescription textualDescription) {
    Ensure.ensureArgumentNotNull(textView);
    Ensure.ensureArgumentNotNull(textualDescription);
    this.textView = textView;
    this.textualDescription = textualDescription;
  }

  public void initPresentation() {
    textView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        textualDescription.setText(newValue);
      }
    });
    textView.setText(textualDescription.getText());
    textualDescription.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        textView.setText(newValue);
      }
    });
  }
}