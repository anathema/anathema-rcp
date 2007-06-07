package net.sf.anathema.basics.jface.text;

import static org.eclipse.swt.SWT.*;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.ObjectValueControl;
import net.sf.anathema.lib.textualdescription.ITextView;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class SimpleTextView implements ITextView {

  private final Text textWidget;
  private final ObjectValueControl<String> control = new ObjectValueControl<String>();

  public static SimpleTextView createSingleLineView(Composite parent) {
    SimpleTextView view = new SimpleTextView(parent, SINGLE);
    view.textWidget.setLayoutData(new GridData(FILL, DEFAULT, true, false));
    return view;
  }

  public static SimpleTextView createMultiLineView(Composite parent) {
    SimpleTextView view = new SimpleTextView(parent, MULTI | WRAP | V_SCROLL);
    view.textWidget.setLayoutData(new GridData(FILL, FILL, true, true));
    return view;
  }

  private SimpleTextView(Composite parent, int lineFlag) {
    this.textWidget = new Text(parent, lineFlag | BORDER);
    this.textWidget.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        control.fireValueChangedEvent(textWidget.getText());
      }
    });
  }

  @Override
  public void addTextChangedListener(IObjectValueChangedListener<String> listener) {
    control.addObjectValueChangeListener(listener);
  }

  @Override
  public void setEnabled(boolean enabled) {
    textWidget.setEnabled(enabled);
  }

  @Override
  public void setText(String text) {
    if (ObjectUtilities.equals(text, textWidget.getText())) {
      return;
    }
    textWidget.setText(text == null ? "" : text); //$NON-NLS-1$
  }

  @Override
  public void setFocus() {
    textWidget.setFocus();
  }
}