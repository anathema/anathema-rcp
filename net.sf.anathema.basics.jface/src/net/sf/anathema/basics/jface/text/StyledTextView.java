package net.sf.anathema.basics.jface.text;

import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.IClosure;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.jface.selection.StyledTextSelectionProvider;
import net.sf.anathema.basics.swt.event.KeyReleasedChangeAdapter;
import net.sf.anathema.basics.swt.event.MouseUpChangeAdapter;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.textualdescription.IStyledTextView;
import net.sf.anathema.lib.textualdescription.ITextExchangeListener;
import net.sf.anathema.lib.textualdescription.ITextFormat;
import net.sf.anathema.lib.textualdescription.ITextPart;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ExtendedModifyEvent;
import org.eclipse.swt.custom.ExtendedModifyListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class StyledTextView implements IStyledTextView {

  private final StyledText contentComposite;
  private final ChangeControl caretMoveControl = new ChangeControl();
  private final GenericControl<ITextExchangeListener> exchangeControl = new GenericControl<ITextExchangeListener>();

  public StyledTextView(Composite parent) {
    contentComposite = new StyledText(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
    contentComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
    contentComposite.addExtendedModifyListener(new ExtendedModifyListener() {
      public void modifyText(ExtendedModifyEvent event) {
        String replacedText = event.replacedText;
        int index = event.start;
        String newText = contentComposite.getTextRange(index, event.length);
        fireExchangeText(index, replacedText.length(), newText);
      }
    });
    final ChangeControl changeControl = caretMoveControl;
    contentComposite.addKeyListener(new KeyReleasedChangeAdapter(changeControl));
    contentComposite.addMouseListener(new MouseUpChangeAdapter(caretMoveControl));
  }

  private void fireExchangeText(final int startIndex, final int replacedTextLength, final String newText) {
    exchangeControl.forAllDo(new IClosure<ITextExchangeListener>() {
      @Override
      public void execute(ITextExchangeListener listener) {
        listener.textReplaced(startIndex, replacedTextLength, newText);
      }
    });
  }

  @Override
  public void setFocus() {
    contentComposite.setFocus();
  }

  @Override
  public void setContent(String newText, ITextPart[] parts) {
    String viewText = contentComposite.getText();
    if (!newText.equals(viewText)) {
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

  @Override
  public void addTextExchangeListener(ITextExchangeListener listener) {
    exchangeControl.addListener(listener);
  }

  public ISelectionProvider createSelectionProvider() {
    return new StyledTextSelectionProvider(contentComposite);
  }

  public Point getSelectionRange() {
    return contentComposite.getSelectionRange();
  }

  public void addCursorPositionChangedListener(IChangeListener changeListener) {
    caretMoveControl.addChangeListener(changeListener);
  }

  public void removeCursorPositionChangedListener(IChangeListener changeListener) {
    caretMoveControl.removeChangeListener(changeListener);
  }
}