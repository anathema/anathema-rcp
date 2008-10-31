package net.sf.anathema.basics.repository.view.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.jface.context.ContextMenuManager;
import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.linkage.EditorViewLinker;
import net.sf.anathema.basics.repository.linkage.ILinkageSelector;
import net.sf.anathema.basics.repository.linkage.ILinker;
import net.sf.anathema.basics.repository.linkage.ViewEditorLinker;
import net.sf.anathema.basics.repository.linkage.util.ILink;
import net.sf.anathema.basics.repository.refresh.ResourceChangeJobScheduler;
import net.sf.anathema.basics.repository.refresh.TreeViewRefresher;
import net.sf.anathema.basics.repository.treecontent.RepositoryLabelProvider;
import net.sf.anathema.basics.repository.treecontent.TypedTreeContentProvider;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.basics.repository.view.IRepositoryDND;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class RepositoryView extends ViewPart implements ILinkageSelector, ILinker, ICollapsableTree, IExpandableTree {
  private static final String DRAG_AND_DROP_EXTENSION_POINT = "dnd"; //$NON-NLS-1$

  public static final String ID = "net.sf.anathema.basics.repositoryview"; //$NON-NLS-1$

  private final List<IDisposable> disposables = new ArrayList<IDisposable>();
  private TreeViewer viewer;
  private TypedTreeContentProvider contentProvider;

  private ViewEditorLinker viewLinker;

  private EditorViewLinker editorLinker;

  @Override
  public void createPartControl(Composite parent) {
    this.viewLinker = new ViewEditorLinker(getSite().getWorkbenchWindow(), this);
    disposables.add(viewLinker);
    this.viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    this.editorLinker = new EditorViewLinker(getSite().getWorkbenchWindow(), viewer);
    ContextMenuManager.connect(getSite(), viewer);
    contentProvider = new TypedTreeContentProvider();
    viewer.setContentProvider(contentProvider);
    viewer.setLabelProvider(new RepositoryLabelProvider());
    viewer.setInput(getViewSite());
    initDragAndDrop();
    viewer.addOpenListener(new IOpenListener() {
      public void open(OpenEvent event) {
        StructuredSelection selection = (StructuredSelection) event.getSelection();
        IViewElement element = (IViewElement) selection.getFirstElement();
        try {
          element.openEditor(getViewSite().getPage());
        }
        catch (PartInitException e) {
          RepositoryPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.RepositoryView_OpenEditorErrorMessage, e);
        }
      }
    });
    viewer.addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        editorLinker.update();
      }
    });
    disposables.add(new ResourceChangeJobScheduler(new TreeViewRefresher(viewer, parent.getDisplay())));
    getSite().setSelectionProvider(viewer);
    viewer.refresh(true);
  }

  private void initDragAndDrop() {
    EclipseExtensionPoint extensionPoint = new EclipseExtensionPoint(RepositoryPlugin.ID, DRAG_AND_DROP_EXTENSION_POINT);
    for (IRepositoryDND dnd : new ClassConveyerBelt<IRepositoryDND>(extensionPoint, IRepositoryDND.class).getAllObjects()) {
      dnd.initDragAndDrop(viewer, getViewSite());
    }
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

  @Override
  public void setSelection(ILink linkageId) {
    RepositoryResourceSelector resourceSelector = new RepositoryResourceSelector(
        contentProvider,
        new RevealingSelector(viewer));
    resourceSelector.setSelection(linkageId);
  }

  @Override
  public void toggleLink() {
    viewLinker.toggleLink();
    editorLinker.toggleLink();
  }

  @Override
  public void collapseAll() {
    viewer.collapseAll();
  }

  @Override
  public void expand(IViewElement element) {
    viewer.expandToLevel(element, AbstractTreeViewer.ALL_LEVELS);
  }

  @Override
  public void dispose() {
    for (IDisposable disposable : disposables) {
      disposable.dispose();
    }
    super.dispose();
  }
}