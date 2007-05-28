package net.sf.anathema.basics.repository;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.jface.context.ContextMenuManager;
import net.sf.anathema.basics.repository.treecontent.RepositoryLabelProvider;
import net.sf.anathema.basics.repository.treecontent.TypedTreeContentProvider;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class RepositoryView extends ViewPart {
  public static final String ID = "net.sf.anathema.basics.repositoryview"; //$NON-NLS-1$

  private TreeViewer viewer;

  @Override
  public void createPartControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    ContextMenuManager.connect(getSite(), viewer);
    viewer.setContentProvider(new TypedTreeContentProvider());
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
    final TreeViewRefresher treeViewRefresher = new TreeViewRefresher(viewer);
    ResourcesPlugin.getWorkspace().addResourceChangeListener(new IResourceChangeListener() {
      @Override
      public void resourceChanged(IResourceChangeEvent event) {
        treeViewRefresher.setRule(ResourcesPlugin.getWorkspace().getRoot());
        treeViewRefresher.schedule();
      }
    });
    viewer.refresh(true);
  }

  private void initDragAndDrop() {
    IPluginExtension[] extensions = new EclipseExtensionProvider().getExtensions("net.sf.anathema.repository.dnd"); //$NON-NLS-1$
    for (IPluginExtension extension : extensions) {
      IExtensionElement[] elements = extension.getElements();
      for (IExtensionElement extensionElement : elements) {
        try {
          IRepositoryDND dnd = extensionElement.getAttributeAsObject("class", IRepositoryDND.class); //$NON-NLS-1$
          dnd.initDragAndDrop(viewer);
        }
        catch (ExtensionException e) {
          RepositoryPlugin.getDefaultInstance()
              .log(IStatus.ERROR, Messages.RepositoryView_InitializeDndErrorMessage, e);
        }
      }
    }
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }
}