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

public class ExperiencePointsView extends DisposableViewPart implements IUpdatableView {

  private TopPartListener topPartListener = new TopPartListener(new Runnable() {
    @Override
    public void run() {
      updateToEditorChange();
    }
  });
  private Composite component;
  private IExperiencePointViewInput viewInput;
  private Composite lastParent;
  private final ExperiencePointViewInputFactory inputFactory = new ExperiencePointViewInputFactory();

  @Override
  public void createPartControl(Composite parent) {
    this.lastParent = parent;
    addDisposable(new PartListening(topPartListener, getSite().getWorkbenchWindow().getPartService()));
    createComposite();
  }

  private void createComposite() {
    IExperiencePointViewInput newInput = getNewExperiencePointViewInput();
    if (newInput == viewInput) {
      return;
    }
    this.viewInput = newInput;
    forceUpdate();
  }

  public void forceUpdate() {
    if (component != null) {
      component.dispose();
    }
    lastParent.setLayout(new GridLayout(1, false));
    component = new Composite(lastParent, SWT.NONE);
    component.setLayoutData(GridDataFactory.createFillBoth());
    component.setLayout(new GridLayout(2, false));
    for (IExperiencePointEntry entry : viewInput.createEntries()) {
      Label nameLabel = new Label(component, SWT.LEFT);
      nameLabel.setText(entry.getModelDisplayName());
      nameLabel.setLayoutData(GridDataFactory.createHorizontalFill());
      Label pointLabel = new Label(component, SWT.RIGHT);
      pointLabel.setText(entry.getExperiencePoints());
      pointLabel.setLayoutData(GridDataFactory.createRightAlign());
    }
    lastParent.layout(true);
  }

  protected void updateToEditorChange() {
    createComposite();
  }

  private IExperiencePointViewInput getNewExperiencePointViewInput() {
    IEditorPart topPart = topPartListener.getTopPart();
    return inputFactory.createEditorInput(topPart, viewInput);
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}