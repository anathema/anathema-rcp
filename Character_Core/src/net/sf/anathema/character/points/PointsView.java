package net.sf.anathema.character.points;

import net.sf.anathema.basics.eclipse.ui.DisposableViewPart;
import net.sf.anathema.basics.eclipse.ui.PartListening;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.basics.item.editor.IEditorInputProvider;
import net.sf.anathema.basics.swt.layout.GridDataFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorPart;

public class PointsView extends DisposableViewPart implements IUpdatableView {

  private TopPartListener topPartListener = new TopPartListener(new Runnable() {
    @Override
    public void run() {
      createComposite();
    }
  });
  private Composite component;
  private IPointViewInput viewInput;
  private Composite lastParent;
  private final PointViewInputFactory inputFactory = new PointViewInputFactory();

  @Override
  public void createPartControl(Composite parent) {
    this.lastParent = parent;
    addDisposable(new PartListening(topPartListener, getSite().getWorkbenchWindow().getPartService()));
    createComposite();
  }

  private void createComposite() {
    IPointViewInput newInput = inputFactory.createEditorInput(getEditorInputProvider(), viewInput);
    if (newInput == viewInput) {
      return;
    }
    this.viewInput = newInput;
    forceUpdate();
  }

  private IEditorInputProvider getEditorInputProvider() {
    IEditorPart activeEditor = getSite().getPage().getActiveEditor();
    return activeEditor instanceof IEditorInputProvider ? (IEditorInputProvider) activeEditor : null;
  }

  public void forceUpdate() {
    if (component != null) {
      component.dispose();
    }
    lastParent.setLayout(new GridLayout(1, false));
    component = new Composite(lastParent, SWT.NONE);
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
    lastParent.layout(true);
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}