package net.sf.anathema.lib.textualdescription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.lib.collection.IClosure;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class StyledTextualDescription extends AbstractTextualDescription implements IStyledTextualDescription {

  private final GenericControl<IStyledTextChangeListener> textListeners = new GenericControl<IStyledTextChangeListener>();
  private List<ITextPart> textParts = new ArrayList<ITextPart>();
  private final Map<ITextPart, Integer> overallStartIndex = new HashMap<ITextPart, Integer>();
  private final Map<IObjectValueChangedListener<String>, IStyledTextChangeListener> listenerMap = new HashMap<IObjectValueChangedListener<String>, IStyledTextChangeListener>();

  public void setText(ITextPart[] textParts) {
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
    int startTextPartIndex = textParts.indexOf(startTextPart);
    int endTextPartIndex = textParts.indexOf(endTextPart);
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

  private ITextPart getTextPart(int textPosition) {
    int endIndex = 0;
    for (ITextPart part : textParts) {
      endIndex += part.getText().length();
      if (endIndex >= textPosition) {
        return part;
      }
    }
    throw new IllegalArgumentException("TextPosition must not be greater than size: " + textPosition); //$NON-NLS-1$
  }
}