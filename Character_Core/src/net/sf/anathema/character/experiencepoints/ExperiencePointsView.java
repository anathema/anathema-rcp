package net.sf.anathema.character.experiencepoints;

import net.sf.anathema.basics.eclipse.ui.DisposableViewPart;
import net.sf.anathema.basics.eclipse.ui.PartListenerDisposable;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ExperiencePointsView  extends DisposableViewPart {
  
  private TopPartListener topPartListener = new TopPartListener(new Runnable() {
    @Override
    public void run() {
      updateToEditorChange();
    }
  });

  @Override
  public void createPartControl(Composite parent) {
    addDisposable(new PartListenerDisposable(topPartListener, getSite().getWorkbenchWindow().getPartService()));
    new Composite(parent, SWT.NONE);
  }

  protected void updateToEditorChange() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}