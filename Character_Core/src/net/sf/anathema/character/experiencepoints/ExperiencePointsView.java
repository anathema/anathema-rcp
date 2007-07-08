package net.sf.anathema.character.experiencepoints;

import net.sf.anathema.basics.eclipse.ui.DisposableViewPart;
import net.sf.anathema.basics.eclipse.ui.PartListening;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ExperiencePointsView extends DisposableViewPart {

  private TopPartListener topPartListener = new TopPartListener(new Runnable() {
    @Override
    public void run() {
      updateToEditorChange();
    }
  });
  private Composite component;

  @Override
  public void createPartControl(Composite parent) {
    addDisposable(new PartListening(topPartListener, getSite().getWorkbenchWindow().getPartService()));
    createComposite(parent);
  }

  private void createComposite(Composite parent) {
    component = new Composite(parent, SWT.NONE);
    component.setLayout(new GridLayout(1, false));
    Label label = new Label(component, SWT.LEFT);
    label.setText(String.valueOf(System.currentTimeMillis()));
    component.pack();
  }

  protected void updateToEditorChange() {
    Composite parent = component.getParent();
    component.dispose();
    createComposite(parent);
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}