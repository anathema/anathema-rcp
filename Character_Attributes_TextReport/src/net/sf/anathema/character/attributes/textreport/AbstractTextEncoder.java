package net.sf.anathema.character.attributes.textreport;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;
import net.sf.anathema.character.textreport.util.ITextReportUtils;
import net.sf.anathema.character.textreport.util.TextPartFactory;

import com.lowagie.text.Chunk;
import com.lowagie.text.Paragraph;

public abstract class AbstractTextEncoder extends AbstractExecutableExtension implements ITextReportEncoder {
  private final ITextReportUtils utils;
  private final TextPartFactory factory;

  public AbstractTextEncoder(ITextReportUtils utils) {
    this.utils = utils;
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

  protected final ITextReportUtils getUtils() {
    return utils;
  }
}