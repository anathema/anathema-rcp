package net.sf.anathema.basics.jface.selection;

import org.eclipse.jface.viewers.ISelection;

public class SimpleSelection implements ISelection {
  
  private final boolean isEmpty;

  public SimpleSelection(boolean isEmpty) {
    this.isEmpty = isEmpty;
  }

  public boolean isEmpty() {
    return isEmpty;
  }
}