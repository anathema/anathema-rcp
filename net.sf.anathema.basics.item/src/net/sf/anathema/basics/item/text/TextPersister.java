package net.sf.anathema.basics.item.text;

import java.util.List;

import net.disy.commons.core.text.font.FontStyle;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextFormat;
import net.sf.anathema.lib.textualdescription.ITextPart;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.TextFormat;
import net.sf.anathema.lib.textualdescription.TextPart;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class TextPersister {

  private static final String TAG_TEXT = "Text"; //$NON-NLS-1$
  private static final String ATTRIB_FONT_STYLE = "fontStyle"; //$NON-NLS-1$
  private static final String ATTRIB_IS_UNDERLINE = "isUnderline"; //$NON-NLS-1$
  private static final String TAG_FORMAT = "Format"; //$NON-NLS-1$
  private static final String TAG_PART = "Part"; //$NON-NLS-1$

  public void saveNonEmptyText(Element parent, String tagName, String text) {
    if (StringUtilities.isNullOrEmpty(text)) {
      return;
    }
    saveText(parent, tagName, text);
  }

  public void saveText(Element parent, String tagName, String text) {
    parent.addElement(tagName).addCDATA(text);
  }

  private void saveNonEmptyText(Element parent, String tagName, ITextPart[] text) {
    if (text.length == 0) {
      return;
    }
    Element textRoot = parent.addElement(tagName);
    for (ITextPart element : text) {
      Element partElement = textRoot.addElement(TAG_PART);
      Element formatElement = partElement.addElement(TAG_FORMAT);
      ITextFormat textFormat = element.getFormat();
      ElementUtilities.addAttribute(formatElement, ATTRIB_IS_UNDERLINE, textFormat.isUnderline());
      formatElement.addAttribute(ATTRIB_FONT_STYLE, textFormat.getFontStyle().getName());
      Element textElement = partElement.addElement(TAG_TEXT);
      textElement.addCDATA(element.getText());
    }
  }

  public void saveTextualDescription(Element parent, String tagName, ITextualDescription textualDescription) {
    saveNonEmptyText(parent, tagName, textualDescription.getText());
  }

  public void saveTextualDescription(Element parent, String tagName, IStyledTextualDescription textualDescription) {
    saveNonEmptyText(parent, tagName, textualDescription.getTextParts());
  }

  public void restoreTextualDescription(Element parent, String tagName, IStyledTextualDescription description) {
    Element textualElement = parent.element(tagName);
    if (textualElement == null) {
      description.setClean();
      return;
    }
    List<Element> partElements = ElementUtilities.elements(textualElement, TAG_PART);
    if (partElements.size() == 0) {
      String textContent = textualElement.getText();
      description.setText(new ITextPart[] { new TextPart(textContent, new TextFormat()) });
      description.setClean();
      return;
    }
    ITextPart[] textParts = new ITextPart[partElements.size()];
    for (int index = 0; index < textParts.length; index++) {
      Element partElement = partElements.get(index);
      textParts[index] = new TextPart(
          partElement.elementText(TAG_TEXT),
          restoreTextFormat(partElement.element(TAG_FORMAT)));
    }
    description.setText(textParts);
    description.setClean();
  }

  private ITextFormat restoreTextFormat(Element formatElement) {
    boolean isUnderline = ElementUtilities.getBooleanAttribute(formatElement, ATTRIB_IS_UNDERLINE, false);
    FontStyle fontStyle = FontStyle.getByName(formatElement.attributeValue(ATTRIB_FONT_STYLE));
    return new TextFormat(fontStyle, isUnderline);
  }

  public void restoreTextualDescription(Element parent, String tagName, ITextualDescription description) {
    Element textualElement = parent.element(tagName);
    if (textualElement != null) {
      description.setText(textualElement.getText());
    }
    description.setClean();
  }
}