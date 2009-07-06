package net.sf.anathema.charms.character.editor.table;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class CharmContentProvider implements IStructuredContentProvider {

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
    ICharmTableInput charmInput = (ICharmTableInput) inputElement;
    return charmInput.getAllCharms();
  }
}