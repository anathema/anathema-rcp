package net.sf.anathema.character.points.view;

import net.sf.anathema.basics.eclipse.ui.DisposableViewPart;
import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.basics.eclipse.ui.PartContainer;
import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.view.valuelist.IUpdatable;
import net.sf.anathema.view.valuelist.IValueEntry;
import net.sf.anathema.view.valuelist.IViewUpdateHandler;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public abstract class AbstractValueListView extends DisposableViewPart implements IUpdatable {

  private Composite component;
  private IPointViewInput viewInput;
  private Composite parent;
  private final IValueListInputStore inputStore;
  private final IViewUpdateHandler updateHandler;

  public AbstractValueListView(IValueListInputStore inputStore, IViewUpdateHandler updateHandler) {
    this.inputStore = inputStore;
    this.updateHandler = updateHandler;
  }

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
    for (IValueEntry entry : viewInput.createEntries()) {
      Label nameLabel = new Label(component, SWT.LEFT);
      nameLabel.setText(entry.getDisplayName());
      nameLabel.setLayoutData(GridDataFactory.createHorizontalFill());
      Label pointLabel = new Label(component, SWT.RIGHT);
      pointLabel.setText(entry.getValue());
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