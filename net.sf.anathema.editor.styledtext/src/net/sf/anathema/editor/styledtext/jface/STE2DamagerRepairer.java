package net.sf.anathema.editor.styledtext.jface;

import static net.sf.anathema.editor.styledtext.jface.STE2Partitioner.*;

import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.swt.SWT;

public class STE2DamagerRepairer extends DefaultDamagerRepairer {

  private final STE2Partitioner partitioner;

  public STE2DamagerRepairer(STE2Partitioner partitioner) {
    super(new RuleBasedScanner());
    this.partitioner = partitioner;
  }

  @Override
  public void createPresentation(TextPresentation presentation, ITypedRegion damage) {
    ITypedRegion[] regions = partitioner.getPartitions(damage);
    for (ITypedRegion region : regions) {
      addRange(presentation, region.getOffset(), region.getLength(), createTextAttribute(region));
    }
  }

  private TextAttribute createTextAttribute(ITypedRegion region) {
    String type = region.getType();
    int styles = 0;
    if (type == BOLD || type == BOLDITALIC || type == BOLDITALICUNDERLINE || type == BOLDUNDERLINE) {
      styles |= SWT.BOLD;
    }
    if (type == ITALIC || type == BOLDITALIC || type == BOLDITALICUNDERLINE || type == ITALICUNDERLINE) {
      styles |= SWT.ITALIC;
    }
    if (type == UNDERLINE || type == BOLDUNDERLINE || type == BOLDITALICUNDERLINE || type == ITALICUNDERLINE) {
      styles |= TextAttribute.UNDERLINE;
    }
    return new TextAttribute(null, null, styles);
  }
}