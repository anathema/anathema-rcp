package net.sf.anathema.character.points;

import net.sf.anathema.basics.eclipse.ui.DisposableViewPart;
import net.sf.anathema.basics.item.editor.IEditorInputProvider;
import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.character.core.model.internal.ModelExtensionPoint;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorPart;

public class PointsView extends DisposableViewPart implements IUpdateable {

  private Composite component;
  private IPointViewInput viewInput;
  private Composite parent;
  private final PointViewInputStore inputStore = new PointViewInputStore(new ModelExtensionPoint());
  private final PointViewUpdateHandler updateHandler = new PointViewUpdateHandler();

  @Override
  public void createPartControl(Composite parentComposite) {
    this.parent = parentComposite;
    updateHandler.init(getSite(), this);
    addDisposable(updateHandler);
    update();
  }

  private IEditorInputProvider getEditorInputProvider() {
    IEditorPart activeEditor = getSite().getPage().getActiveEditor();
    return activeEditor instanceof IEditorInputProvider ? (IEditorInputProvider) activeEditor : null;
  }

  public void update() {
    if (component != null) {
      component.dispose();
    }
    IPointViewInput newInput = inputStore.getViewInput(getEditorInputProvider());
    if (newInput == viewInput) {
      return;
    }
    this.viewInput = newInput;
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
    updateName();
  }

  private void updateName() {
    setPartName(updateHandler.getTitle());
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}