package net.sf.anathema.editor.styledtext.jface;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription2;
import net.sf.anathema.lib.textualdescription.ITextAspectToggle;
import net.sf.anathema.lib.textualdescription.ITextPart;
import net.sf.anathema.lib.textualdescription.TextAspect;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.DocumentRewriteSession;
import org.eclipse.jface.text.DocumentRewriteSessionType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension4;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IDocumentPartitionerExtension;
import org.eclipse.jface.text.IDocumentPartitionerExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TypedRegion;

public final class STE2Partitioner implements
    IDocumentPartitioner,
    IDocumentPartitionerExtension,
    IDocumentPartitionerExtension3,
    ITextAspectToggle {

  public final static String BOLD = "BOLD"; //$NON-NLS-1$
  public final static String ITALIC = "ITALIC"; //$NON-NLS-1$
  public final static String UNDERLINE = "UNDERLINE"; //$NON-NLS-1$
  public final static String BOLDITALIC = "BOLDITALIC"; //$NON-NLS-1$
  public final static String BOLDUNDERLINE = "BOLDUNDERLINE"; //$NON-NLS-1$
  public final static String ITALICUNDERLINE = "ITALICUNDERLINE"; //$NON-NLS-1$
  public final static String BOLDITALICUNDERLINE = "BOLDITALICUNDERLINE"; //$NON-NLS-1$
  public final static String[] CONTENT_TYPES = new String[] {
      BOLD,
      BOLDITALIC,
      BOLDUNDERLINE,
      BOLDITALICUNDERLINE,
      ITALICUNDERLINE,
      ITALIC,
      UNDERLINE };

  private IDocument document;
  private DocumentRewriteSession session;
  private IStyledTextualDescription2 content;

  @Override
  public void toggleAspect(TextAspect aspect, int offset, int length) {
    content.toggleAspect(aspect, offset, length);
    if (document instanceof IDocumentExtension4) {
      DocumentRewriteSession session2 = ((IDocumentExtension4) document).startRewriteSession(DocumentRewriteSessionType.UNRESTRICTED_SMALL);
      ((IDocumentExtension4) document).stopRewriteSession(session2);
    }
  }

  @Override
  public ITypedRegion[] computePartitioning(int offset, int length) {
    List<ITypedRegion> regions = new ArrayList<ITypedRegion>();
    for (ITextPart part : content.getParts(offset, length)) {
      regions.add(createTypedRegion(part));
    }
    return regions.toArray(new ITypedRegion[regions.size()]);
  }

  @Override
  public void documentAboutToBeChanged(DocumentEvent event) {
    // nothing to do
  }

  @Override
  public IRegion documentChanged2(DocumentEvent event) {
    // TODO Implement
    return new Region(event.getOffset(), event.getLength());
  }

  @Override
  public boolean documentChanged(DocumentEvent event) {
    return documentChanged2(event) != null;
  }

  @Override
  public String getContentType(int offset) {
    ITypedRegion partition = getPartition(offset);
    if (partition == null) {
      return IDocument.DEFAULT_CONTENT_TYPE;
    }
    return partition.getType();
  }

  @Override
  public String[] getLegalContentTypes() {
    return CONTENT_TYPES;
  }

  @Override
  public ITypedRegion getPartition(int offset) {
    ITextPart part = content.getPart(offset);
    return createTypedRegion(part);
  }

  private TypedRegion createTypedRegion(ITextPart part) {
    return new TypedRegion(content.getPartOffset(part), part.getLength(), getStyle(part));
  }

  private String getStyle(ITextPart part) {
    FontStyle style = part.getFormat().getFontStyle();
    if (style == FontStyle.BOLD) {
      if (part.getFormat().isUnderline()) {
        return BOLDUNDERLINE;
      }
      return BOLD;
    }
    if (part.getFormat().getFontStyle() == FontStyle.BOLD_ITALIC) {
      if (part.getFormat().isUnderline()) {
        return BOLDITALICUNDERLINE;
      }
      return BOLDITALIC;
    }
    if (part.getFormat().getFontStyle() == FontStyle.ITALIC) {
      if (part.getFormat().isUnderline()) {
        return ITALICUNDERLINE;
      }
      return ITALIC;
    }
    if (part.getFormat().isUnderline()) {
      return UNDERLINE;
    }
    return IDocument.DEFAULT_CONTENT_TYPE;
  }

  public ITypedRegion[] getPartitions(ITypedRegion region) {
    return computePartitioning(region.getOffset(), region.getLength());
  }

  @Override
  public DocumentRewriteSession getActiveRewriteSession() {
    return session;
  }

  @Override
  public void startRewriteSession(DocumentRewriteSession newSession) throws IllegalStateException {
    if (this.session != null) {
      throw new IllegalStateException("Session already running.");
    }
    this.session = newSession;
  }

  @Override
  public void stopRewriteSession(DocumentRewriteSession oldSession) {
    if (session == this.session) {
      this.session = null;
    }
  }

  @Override
  public void connect(IDocument newDocument) {
    this.document = newDocument;
  }

  @Override
  public void connect(IDocument newDocument, boolean delayInitialization) {
    connect(document);
  }

  @Override
  public void disconnect() {
    this.document = null;
  }

  public void setPartitioning(IStyledTextualDescription2 content) {
    this.content = content;
  }
}