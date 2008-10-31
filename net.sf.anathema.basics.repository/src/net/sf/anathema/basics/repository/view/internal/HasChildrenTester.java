package net.sf.anathema.basics.repository.view.internal;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.expressions.PropertyTester;

public class HasChildrenTester extends PropertyTester {

  private static final String HAS_CHILDREN = "hasChildren"; //$NON-NLS-1$

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    IViewElement element = (IViewElement) receiver;
    if (HAS_CHILDREN.equals(property)) {
      return element.hasChildren();
    }
    throw new UnreachableCodeReachedException("Tester was declared for untested property."); //$NON-NLS-1$
  }
}
