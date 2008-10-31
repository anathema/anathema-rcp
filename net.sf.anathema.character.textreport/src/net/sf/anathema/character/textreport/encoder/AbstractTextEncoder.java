package net.sf.anathema.character.textreport.encoder;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.textreport.util.TextPartFactory;
import net.sf.anathema.character.textreport.util.TextReportUtils;

import com.lowagie.text.Chunk;
import com.lowagie.text.Paragraph;

public abstract class AbstractTextEncoder extends AbstractExecutableExtension implements ITextReportEncoder {
  private final TextReportUtils utils = new TextReportUtils();
  private final TextPartFactory factory;

  public AbstractTextEncoder() {
    this.factory = new TextPartFactory(utils);
  }

  protected final Paragraph createTextParagraph(Chunk chunk) {
    return factory.createTextParagraph(chunk);
  }

  protected final Chunk createTextChunk(String text) {
    return factory.createTextChunk(text);
  }

  protected final Chunk createBoldTitle(String title) {
    return factory.createBoldTitle(title);
  }

  protected final TextReportUtils getUtils() {
    return utils;
  }
}