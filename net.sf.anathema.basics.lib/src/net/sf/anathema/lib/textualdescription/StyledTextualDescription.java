package net.sf.anathema.lib.textualdescription;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.disy.commons.core.util.IClosure;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.lib.control.ChangeManagement;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class StyledTextualDescription extends ChangeManagement implements
    IStyledTextualDescription,
    IStyledTextualDescription2 {

  private final GenericControl<IStyledTextChangeListener> textListeners = new GenericControl<IStyledTextChangeListener>();
  private final TextPartCollection textPartCollection = new TextPartCollection();
  private final Map<IObjectValueChangedListener<String>, IStyledTextChangeListener> listenerMap = new HashMap<IObjectValueChangedListener<String>, IStyledTextChangeListener>();

  public void setText(ITextPart... textParts) {
    textPartCollection.setText(textParts);
    fireTextChangedEvent();
  }

  public ITextPart[] getTextParts() {
    return textPartCollection.getTextParts();
  }

  private void fireTextChangedEvent() {
    setDirty(true);
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
    return textPartCollection.isEmpty();
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
        listener.valueChanged(getText());
      }
    };
    addTextChangedListener(styledListener);
    listenerMap.put(listener, styledListener);
  }

  public void removeTextChangeListener(IObjectValueChangedListener<String> listener) {
    removeTextChangedListener(listenerMap.get(listener));
  }

  public String getText() {
    return textPartCollection.getText();
  }

  @Override
  public void toggleAspect(final TextAspect aspect, final int offset, final int length) {
    if (textPartCollection.isEmpty()) {
      return;
    }
    ITransformer<ITextFormat, ITextFormat> transformer = new ITransformer<ITextFormat, ITextFormat>() {
      @Override
      public ITextFormat transform(ITextFormat input) {
        return aspect.deriveFormat(input, isDominant(aspect, offset, length));
      }
    };
    TextModificationBlock block = new TextModificationBlock(textPartCollection, offset, length);
    int currentOffset = offset;
    for (int index = block.getStartTextPartIndex(); index <= block.getEndTextPartIndex(); index++) {
      ITextPart currentPart = textPartCollection.get(index);
      final int partStart = textPartCollection.getStartPosition(currentPart);
      int partLength = currentPart.getText().length();
      ITextFormat toggledFormat = transformer.transform(currentPart.getFormat());
      if (currentOffset == partStart) {
        int firstPartLength = Math.min(partLength, block.getTailStartPosition() - partStart);
        ITextPart[] splittedParts = currentPart.split(0, firstPartLength);
        block.add(new TextPart(splittedParts[0].getText(), toggledFormat));
        if (splittedParts.length > 1) {
          block.add(splittedParts[1]);
        }
        currentOffset += partLength;
      }
      else {
        int middlePartLength = Math.min(partLength, block.getTailStartPosition() - partStart);
        ITextPart[] splittedParts = currentPart.split(currentOffset - partStart, middlePartLength);
        block.add(splittedParts[0]);
        block.add(new TextPart(splittedParts[1].getText(), toggledFormat));
        if (splittedParts.length > 2) {
          block.add(splittedParts[2]);
        }
        currentOffset += splittedParts[1].getText().length();
      }
    }
    block.commit();
    fireTextChangedEvent();
  }

  public void replaceText(int startTextPosition, int length, String newText) {
    TextModificationBlock block = new TextModificationBlock(textPartCollection, startTextPosition, length);
    if (textPartCollection.isEmpty()) {
      block.add(new TextPart(newText, new TextFormat()));
    }
    else {
      ITextPart tailingTextPart = textPartCollection.getTextPartByTextPosition(block.getTailStartPosition());
      int startIndexWithinTextPart = startTextPosition - textPartCollection.getStartPosition(block.getStartTextPart());
      String originalText = block.getStartTextPart().getText();
      StringBuilder newTextBuilder = new StringBuilder();
      newTextBuilder.append(originalText.substring(0, startIndexWithinTextPart));
      newTextBuilder.append(newText);
      int endIndexWithinEndTextPart = block.getTailStartPosition()
          - textPartCollection.getStartPosition(tailingTextPart);
      String endTextPartText = tailingTextPart.getText().substring(endIndexWithinEndTextPart);
      if (block.getStartTextPart().getFormat().equals(tailingTextPart.getFormat())) {
        newTextBuilder.append(endTextPartText);
        block.add(new TextPart(newTextBuilder.toString(), block.getStartTextPart().getFormat()));
      }
      else {
        block.add(new TextPart(newTextBuilder.toString(), block.getStartTextPart().getFormat()));
        block.add(new TextPart(endTextPartText, tailingTextPart.getFormat()));
      }
    }
    block.commit();
    fireTextChangedEvent();
  }

  @Override
  public boolean isDominant(final TextAspect aspect, int offset, int length) {
    if (textPartCollection.isEmpty()) {
      return false;
    }
    TextModificationBlock block = new TextModificationBlock(textPartCollection, offset, length);
    int startTextPartIndex = block.getStartTextPartIndex();
    int endTextPartIndex = block.getEndTextPartIndex();
    for (int index = startTextPartIndex; index <= endTextPartIndex; index++) {
      ITextPart currentPart = textPartCollection.get(index);
      if (!aspect.isDominant(currentPart.getFormat())) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int getTextChangeListenerCount() {
    return textListeners.getListenerCount();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof StyledTextualDescription)) {
      return false;
    }
    return Arrays.equals(
        textPartCollection.getTextParts(),
        ((StyledTextualDescription) obj).textPartCollection.getTextParts());
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(textPartCollection.getTextParts());
  }

  @Override
  public ITextPart getPart(int offset) {
    return textPartCollection.getTextPartByTextPosition(offset);
  }

  @Override
  public int getPartOffset(ITextPart part) {
    return textPartCollection.getStartPosition(part);
  }

  @Override
  public List<ITextPart> getParts(int offset, int length) {
    ITextPart part1 = textPartCollection.getTextPartByTextPosition(offset);
    int index1 = textPartCollection.indexOf(part1);
    ITextPart part2 = textPartCollection.getTextPartByTextPosition(offset + length);
    int index2 = textPartCollection.indexOf(part2);
    return textPartCollection.subList(index1, index2);
  }
}