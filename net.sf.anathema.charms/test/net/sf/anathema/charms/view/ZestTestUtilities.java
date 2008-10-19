package net.sf.anathema.charms.view;

import java.lang.reflect.Method;

import org.eclipse.zest.core.widgets.GraphNode;

public class ZestTestUtilities {

  public static Boolean callIsHighlighted(GraphNode graphNode) throws Exception {
    Class< ? > graphNodeClass = Class.forName(GraphNode.class.getName());
    Method method = graphNodeClass.getDeclaredMethod("isHighlighted"); //$NON-NLS-1$
    method.setAccessible(true);
    return (Boolean) method.invoke(graphNode);
  }

}
