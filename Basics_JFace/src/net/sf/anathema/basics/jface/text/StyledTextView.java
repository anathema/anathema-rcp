package net.sf.anathema.basics.jface.text;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.lib.textualdescription.IStyledTextView;
import net.sf.anathema.lib.textualdescription.ITextFormat;
import net.sf.anathema.lib.textualdescription.ITextPart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class StyledTextView implements IStyledTextView {

  private StyledText contentComposite;

  public StyledTextView(Composite parent) {
    contentComposite = new StyledText(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
    contentComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
  }

  @Override
  public void setFocus() {
    contentComposite.setFocus();
  }

  @Override
  public void setContent(String newText, ITextPart[] parts) {
    if (!newText.equals(contentComposite.getText())) {
      contentComposite.setText(newText);
    }
    contentComposite.setStyleRanges(createStyleRanges(parts));
  }

  private StyleRange[] createStyleRanges(final ITextPart[] textParts) {
    final StyleRangeFactory styleRangeFactory = new StyleRangeFactory();
    final int[] startIndex = new int[] { 0 };
    StyleRange[] styleRanges = ArrayUtilities.transform(
        textParts,
        StyleRange.class,
        new ITransformer<ITextPart, StyleRange>() {
          public StyleRange transform(ITextPart textPart) {
            int length = textPart.getText().length();
            ITextFormat format = textPart.getFormat();
            StyleRange styleRange = styleRangeFactory.createStyleRange(startIndex[0], length, format);
            startIndex[0] += length;
            return styleRange;
          }
        });
    return styleRanges;
  }
}