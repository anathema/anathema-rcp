package net.sf.anathema.lib.textualdescription;

import java.util.ArrayList;
import java.util.List;

public class TextModificationBlock {

  private final TextPartCollection textPartCollection;
  private final int offset;
  private final int length;
  private final List<ITextPart> newTextParts = new ArrayList<ITextPart>();

  public TextModificationBlock(TextPartCollection textPartCollection, int offset, int length) {
    this.textPartCollection = textPartCollection;
    this.offset = offset;
    this.length = length;
    if (offset > 0) {
      addLeadingTextParts();
    }
  }

  public ITextPart getEndTextPart() {
    return textPartCollection.getTextPartByTextPosition(getBlockEndPosition());
  }

  public int getEndTextPartIndex() {
    return textPartCollection.indexOf(getEndTextPart());
  }

  public ITextPart getStartTextPart() {
    return textPartCollection.getTextPartByTextPosition(offset);
  }

  public int getStartTextPartIndex() {
    return textPartCollection.indexOf(getStartTextPart());
  }

  public int getBlockEndPosition() {
    return offset + length - 1;
  }

  public int getTailStartPosition() {
    return getBlockEndPosition() + 1;
  }

  private void addTailingTextParts() {
    int tailStartIndex = getEndTextPartIndex() + 1;
    if (tailStartIndex < textPartCollection.size()) {
      newTextParts.addAll(textPartCollection.subList(tailStartIndex, textPartCollection.size()));
    }
  }

  private void addLeadingTextParts() {
    newTextParts.addAll(textPartCollection.subList(0, getStartTextPartIndex()));
  }

  public void commit() {
    if (!textPartCollection.isEmpty()) {
      addTailingTextParts();
    }
    textPartCollection.setText(newTextParts.toArray(new ITextPart[newTextParts.size()]));
  }

  public void add(ITextPart textPart) {
    newTextParts.add(textPart);
  }
}