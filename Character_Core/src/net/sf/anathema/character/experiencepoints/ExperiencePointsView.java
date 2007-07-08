package net.sf.anathema.character.experiencepoints;

import net.sf.anathema.basics.eclipse.ui.DisposableViewPart;
import net.sf.anathema.basics.eclipse.ui.PartListening;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.basics.swt.layout.GridDataFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorPart;

public class ExperiencePointsView extends DisposableViewPart {

  private TopPartListener topPartListener = new TopPartListener(new Runnable() {
    @Override
    public void run() {
      updateToEditorChange();
    }
  });
  private Composite component;
  private ExperiencePointViewInput viewInput;
  private Composite lastParent;

  @Override
  public void createPartControl(Composite parent) {
    this.lastParent = parent;
    addDisposable(new PartListening(topPartListener, getSite().getWorkbenchWindow().getPartService()));
    createComposite();
  }

  private void createComposite() {
    ExperiencePointViewInput newInput = getNewExperiencePointViewInput();
    if (newInput == viewInput) {
      return;
    }
    lastParent.setLayout(new GridLayout(1, false));
    component = new Composite(lastParent, SWT.NONE);
    component.setLayoutData(GridDataFactory.createFillBoth());
    component.setLayout(new GridLayout(2, false));
    for (IExperiencePointEntry entry : newInput.getEntries()) {
      Label nameLabel = new Label(component, SWT.LEFT);
      nameLabel.setText(entry.getModelDisplayName());
      nameLabel.setLayoutData(GridDataFactory.createHorizontalFill());
      Label pointLabel = new Label(component, SWT.RIGHT);
      pointLabel.setText(String.valueOf(entry.getExperiencePoints()));
      pointLabel.setLayoutData(GridDataFactory.createRightAlign());
    }
    lastParent.layout(true);
  }

  protected void updateToEditorChange() {
    component.dispose();
    createComposite();
  }

  private ExperiencePointViewInput getNewExperiencePointViewInput() {
    IEditorPart topPart = topPartListener.getTopPart();
    return new ExperiencePointViewInputFactory().getEditorInput(topPart, viewInput);
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}