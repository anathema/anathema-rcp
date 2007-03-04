package net.sf.anathema.basics.jface.text;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.ObjectValueControl;
import net.sf.anathema.lib.textualdescription.ITextView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class SimpleTextView implements ITextView {

  private final Text textWidget;
  private final ObjectValueControl<String> control = new ObjectValueControl<String>();

  public SimpleTextView(Composite parent) {
    this.textWidget = new Text(parent, SWT.SINGLE | SWT.BORDER);
    this.textWidget.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        control.fireValueChangedEvent(textWidget.getText());
      }
    });
    textWidget.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
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
    textWidget.setText(text);
  }
}