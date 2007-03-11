package net.sf.anathema.lib.textualdescription;

import net.disy.commons.core.util.Ensure;

public class StyledTextPresenter {

  private final IStyledTextView view;
  private final IStyledTextualDescription description;

  public StyledTextPresenter(final IStyledTextView view, final IStyledTextualDescription description) {
    Ensure.ensureArgumentNotNull(view);
    Ensure.ensureArgumentNotNull(description);
    this.view = view;
    this.description = description;
  }

  public void initPresentation() {
    view.addTextExchangeListener(new ITextExchangeListener() {
      @Override
      public void textReplaced(int startIndex, int replacedTextLength, String newText) {
        description.replaceText(startIndex, replacedTextLength, newText);
      }
    });
    description.addTextChangedListener(new IStyledTextChangeListener() {
      public void textChanged(ITextPart[] newParts) {
        updateContent();
      }
    });
    updateContent();
  }

  private void updateContent() {
    view.setContent(description.getText(), description.getTextParts());
  }
}