package net.sf.anathema.lib.textualdescription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextPartCollection {

  private List<ITextPart> textParts = new ArrayList<ITextPart>();
  private final Map<ITextPart, Integer> overallStartPosition = new HashMap<ITextPart, Integer>();

  public void setText(ITextPart[] textParts) {
    if (Arrays.deepEquals(getTextParts(), textParts)) {
      return;
    }
    this.textParts = new ArrayList<ITextPart>();
    Collections.addAll(this.textParts, textParts);
    overallStartPosition.clear();
    int startIndex = 0;
    for (ITextPart part : textParts) {
      overallStartPosition.put(part, startIndex);
      startIndex += part.getText().length();
    }
  }

  public ITextPart[] getTextParts() {
    return textParts.toArray(new ITextPart[textParts.size()]);
  }

  public boolean isEmpty() {
    return textParts.size() == 0;
  }

  public int indexOf(ITextPart textPart) {
    for (int index = 0; index < textParts.size(); index++) {
      if (textParts.get(index) == textPart) {
        return index;
      }
    }
    return -1;
  }

  public ITextPart getTextPartByTextPosition(int textPosition) {
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

  public String getText() {
    return getText(getTextParts());
  }

  private String getText(ITextPart[] parts) {
    StringBuilder builder = new StringBuilder();
    for (ITextPart part : parts) {
      builder.append(part.getText());
    }
    return builder.toString();
  }

  public ITextPart get(int index) {
    return textParts.get(index);
  }

  public int getStartPosition(ITextPart textPart) {
    return  overallStartPosition.get(textPart);
  }

  public int size() {
    return textParts.size();
  }

  public List<ITextPart> subList(int fromIndex, int toIndex) {
    return textParts.subList(fromIndex, toIndex);
  }

  public int getIndexOfTextPosition(int offset) {
    return  indexOf(getTextPartByTextPosition(offset));
  }
}