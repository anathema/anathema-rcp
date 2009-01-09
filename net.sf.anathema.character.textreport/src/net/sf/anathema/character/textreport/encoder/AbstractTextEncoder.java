package net.sf.anathema.character.textreport.encoder;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.textreport.util.TextPartFactory;
import net.sf.anathema.character.textreport.util.TextReportUtils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.lowagie.text.Chunk;
import com.lowagie.text.Paragraph;

public abstract class AbstractTextEncoder implements ITextReportEncoder {
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_REFERENCE_ID = "referenceId"; //$NON-NLS-1$
  private final TextReportUtils utils = new TextReportUtils();
  private final TextPartFactory factory;
  private String id;
  private final List<String> afterIds = new ArrayList<String>();

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

  @Override
  public final void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    this.id = config.getAttribute(ATTRIB_ID);
    for (IConfigurationElement child : config.getChildren()) {
      afterIds.add(child.getAttribute(ATTRIB_REFERENCE_ID));
    }
  }

  public boolean containsAfter(String after) {
    return afterIds.contains(after);
  }

  public String getId() {
    return id;
  }

  @Override
  public int compareTo(ITextReportEncoder encoder) {
    if (containsAfter(encoder.getId())) {
      return 1;
    }
    if (encoder.containsAfter(getId())) {
      return -1;
    }
    return 0;
  }
}