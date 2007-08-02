package net.sf.anathema.character.points.model;

import net.sf.anathema.basics.eclipse.ui.DisposableViewPart;
import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.basics.eclipse.ui.PartContainer;
import net.sf.anathema.basics.swt.layout.GridDataFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PointsView extends DisposableViewPart implements IUpdatable {

  private Composite component;
  private IPointViewInput viewInput;
  private Composite parent;
  private final PointViewInputStore inputStore = new PointViewInputStore();
  private final PointViewUpdateHandler updateHandler = new PointViewUpdateHandler();

  @Override
  public void createPartControl(Composite parentComposite) {
    this.parent = parentComposite;
    updateHandler.init(getPartContainer(), this);
    addDisposable(updateHandler);
    update();
  }

  public void update() {
    if (component != null) {
      component.dispose();
    }
    this.viewInput = inputStore.getViewInput(getPartContainer().getEditorInput());
    parent.setLayout(new GridLayout(1, false));
    component = new Composite(parent, SWT.NONE);
    component.setLayoutData(GridDataFactory.createFillBoth());
    component.setLayout(new GridLayout(2, false));
    for (IPointEntry entry : viewInput.createEntries()) {
      Label nameLabel = new Label(component, SWT.LEFT);
      nameLabel.setText(entry.getModelDisplayName());
      nameLabel.setLayoutData(GridDataFactory.createHorizontalFill());
      Label pointLabel = new Label(component, SWT.RIGHT);
      pointLabel.setText(entry.getExperiencePoints());
      pointLabel.setLayoutData(GridDataFactory.createRightAlign());
    }
    parent.layout(true);
    setPartName(updateHandler.getTitle());
  }

  private IPartContainer getPartContainer() {
    return new PartContainer(getSite().getWorkbenchWindow());
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}