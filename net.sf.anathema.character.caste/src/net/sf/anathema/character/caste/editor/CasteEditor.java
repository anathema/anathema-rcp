package net.sf.anathema.character.caste.editor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.core.editors.AbstractCharacterModelEditorPart;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class CasteEditor extends AbstractCharacterModelEditorPart<ICasteModel> {

  private static final class CasteLabelProvider extends LabelProvider {
    @Override
    public String getText(Object element) {
      return ((ICaste) element).getPrintName();
    }
  }

  private static final class CasteSelectionChangedListener implements ISelectionChangedListener {
    private final ICasteModel item;

    private CasteSelectionChangedListener(ICasteModel item) {
      this.item = item;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
      ICaste caste = (ICaste) ((IStructuredSelection) event.getSelection()).getFirstElement();
      item.setCaste(caste);
    }
  }

  private static final class CasteContentProvider implements IStructuredContentProvider {
    @Override
    public void dispose() {
      // nothing to do
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
      // nothing to do
    }

    @Override
    public Object[] getElements(Object inputElement) {
      return ((ICasteModel) inputElement).getOptions();
    }
  }

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void setFocus() {
        // nothing to do
      }

      @Override
      protected void createPartControl(FormToolkit toolkit, Composite body) {
        body.setLayout(new GridLayout(2, false));
        toolkit.createLabel(body, Messages.CasteEditor_CasteLabel);
        IContentProvider provider = new CasteContentProvider();
        ComboViewer viewer = new ComboViewer(body);
        viewer.setContentProvider(provider);
        viewer.setLabelProvider(new CasteLabelProvider());
        final ICasteModel item = getPersistableEditorInput().getItem();
        viewer.setInput(item);
        viewer.addSelectionChangedListener(new CasteSelectionChangedListener(item));
        ICaste caste = item.getCaste();
        if (caste != null) {
          viewer.setSelection(new StructuredSelection(caste));
        }
        final CasteEditorInput casteEditorInput = ((CasteEditorInput) getEditorInput());
        final Combo combo = viewer.getCombo();
        casteEditorInput.addModifiableListener(new IChangeListener() {
          @Override
          public void stateChanged() {
            combo.setEnabled(casteEditorInput.isModifiable());
          }
        });
        combo.setEnabled(casteEditorInput.isModifiable());
      }
    };
  }
}