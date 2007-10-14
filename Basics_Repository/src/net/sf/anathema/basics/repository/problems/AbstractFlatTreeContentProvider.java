package net.sf.anathema.basics.repository.problems;

import org.eclipse.jface.viewers.ITreeContentProvider;

public abstract class AbstractFlatTreeContentProvider implements ITreeContentProvider {

  @Override
  public final Object[] getChildren(Object parentElement) {
    return new Object[0];
  }

  @Override
  public final Object getParent(Object element) {
    return null;
  }

  @Override
  public final boolean hasChildren(Object element) {
    return false;
  }
}