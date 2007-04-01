package net.sf.anathema.lib.textualdescription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.lib.collection.IClosure;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class StyledTextualDescription extends AbstractTextualDescription implements IStyledTextualDescription {

  private final GenericControl<IStyledTextChangeListener> textListeners = new GenericControl<IStyledTextChangeListener>();
  private List<ITextPart> textParts = new ArrayList<ITextPart>();
  private final Map<ITextPart, Integer> overallStartIndex = new HashMap<ITextPart, Integer>();
  private final Map<IObjectValueChangedListener<String>, IStyledTextChangeListener> listenerMap = new HashMap<IObjectValueChangedListener<String>, IStyledTextChangeListener>();

  public void setText(ITextPart... textParts) {
    if (Arrays.deepEquals(getTextParts(), textParts)) {
      return;
    }
    this.textParts = new ArrayList<ITextPart>();
    Collections.addAll(this.textParts, textParts);
    overallStartIndex.clear();
    int startIndex = 0;
    for (ITextPart part : textParts) {
      overallStartIndex.put(part, startIndex);
      startIndex += part.getText().length();
    }
    setDirty(true);
  }

  public ITextPart[] getTextParts() {
    return textParts.toArray(new ITextPart[textParts.size()]);
  }

  @Override
  protected void fireChangedEvent() {
    textListeners.forAllDo(new IClosure<IStyledTextChangeListener>() {
      public void execute(IStyledTextChangeListener input) {
        input.textChanged(getTextParts());
      }
    });
  }

  public void addTextChangedListener(IStyledTextChangeListener listener) {
    textListeners.addListener(listener);
  }

  public void removeTextChangedListener(IStyledTextChangeListener listener) {
    textListeners.removeListener(listener);
  }

  public boolean isEmpty() {
    return textParts.size() == 0;
  }

  public void setText(String text) {
    if (text == null) {
      setText(new ITextPart[0]);
    }
    else {
      setText(new ITextPart[] { new TextPart(text, new TextFormat()) });
    }
  }

  public void addTextChangedListener(final IObjectValueChangedListener<String> listener) {
    IStyledTextChangeListener styledListener = new IStyledTextChangeListener() {
      public void textChanged(ITextPart[] newParts) {
        listener.valueChanged(getText(newParts));
      }
    };
    addTextChangedListener(styledListener);
    listenerMap.put(listener, styledListener);
  }

  public void removeTextChangeListener(IObjectValueChangedListener<String> listener) {
    removeTextChangedListener(listenerMap.get(listener));
  }

  private String getText(ITextPart[] parts) {
    StringBuilder builder = new StringBuilder();
    for (ITextPart part : parts) {
      builder.append(part.getText());
    }
    return builder.toString();
  }

  public String getText() {
    return getText(getTextParts());
  }

  public void replaceText(int startTextPosition, int length, String newText) {
    int endTextPosition = startTextPosition + length;
    ITextPart startTextPart = getTextPart(startTextPosition);
    ITextPart endTextPart = getTextPart(endTextPosition);
    int startTextPartIndex = indexOfInstance(startTextPart);
    int endTextPartIndex = indexOfInstance(endTextPart);
    List<ITextPart> newTextParts = new ArrayList<ITextPart>();
    newTextParts.addAll(textParts.subList(0, startTextPartIndex));
    int startIndexWithinTextPart = startTextPosition - overallStartIndex.get(startTextPart);
    String originalText = startTextPart.getText();
    StringBuilder newTextBuilder = new StringBuilder();
    newTextBuilder.append(originalText.substring(0, startIndexWithinTextPart));
    newTextBuilder.append(newText);
    int endIndexWithinEndTextPart = endTextPosition - overallStartIndex.get(endTextPart);
    String endTextPartText = endTextPart.getText().substring(endIndexWithinEndTextPart);
    if (startTextPart.getFormat().equals(endTextPart.getFormat())) {
      newTextBuilder.append(endTextPartText);
      newTextParts.add(new TextPart(newTextBuilder.toString(), startTextPart.getFormat()));
    }
    else {
      newTextParts.add(new TextPart(newTextBuilder.toString(), startTextPart.getFormat()));
      newTextParts.add(new TextPart(endTextPartText, endTextPart.getFormat()));
    }
    if (endTextPartIndex + 1 < textParts.size()) {
      newTextParts.addAll(textParts.subList(endTextPartIndex + 1, textParts.size()));
    }
    setText(newTextParts.toArray(new ITextPart[newTextParts.size()]));
  }

  private int indexOfInstance(ITextPart textPart) {
    for (int index = 0; index < textParts.size(); index++) {
      if (textParts.get(index) == textPart) {
        return index;
      }
    }
    return -1;
  }

  private ITextPart getTextPart(int textPosition) {
    int endIndex = 0;
    for (ITextPart part : textParts) {
      endIndex += part.getText().length();
      if (endIndex > textPosition) {
        return part;
      }
    }
    if (textPosition == getText().length()) {
      return textParts.get(textParts.size() - 1);
    }
    throw new IllegalArgumentException("TextPosition must not be greater than size: " + textPosition); //$NON-NLS-1$
  }

  @Override
  public boolean containsFormat(int offset, int length, IPredicate<ITextFormat> predicate) {
    if (textParts.size() == 0) {
      return false;
    }
    int firstIndex = indexOfInstance(getTextPart(offset));
    int endIndex = indexOfInstance(getTextPart(offset + length - 1));
    for (int index = firstIndex; index <= endIndex; index++) {
      ITextPart currentPart = textParts.get(index);
      if (predicate.evaluate(currentPart.getFormat())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void toggleFontStyle(int offset, int length, FontStyle fontStyle) {
    // int endIndex = indexOfInstance(getTextPart(offset + length));
    // ITextPart endTextPart = textParts.get(endIndex);
    int startIndex = indexOfInstance(getTextPart(offset));
    ITextPart startTextPart = textParts.get(startIndex);
    List<ITextPart> newTextParts = new ArrayList<ITextPart>();
    int splitPoint = offset == 0 ? length : offset;
    ITextPart[] splittedParts = startTextPart.split(splitPoint);
    ITextFormat toggledFormat = TextFormat.deriveFormat(startTextPart.getFormat(), fontStyle);
    if (offset == 0) {
      newTextParts.add(new TextPart(splittedParts[0].getText(), toggledFormat));
      if (splittedParts.length > 1) {
        newTextParts.add(splittedParts[1]);
      }
    }
    else {
      newTextParts.add(splittedParts[0]);
      newTextParts.add(new TextPart(splittedParts[1].getText(), toggledFormat));
    }
    setText(newTextParts.toArray(new ITextPart[newTextParts.size()]));
  }

  @Override
  public void toggleUnderline(int offset, int length) {
    // TODO setUnderline
  }
}