package net.sf.anathema.character.trait.groupeditor.internal;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.trait.groupeditor.TraitGroupEditor;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.ui.IEditorPart;

public class IsTraitGroupEditorTester extends PropertyTester {

  private static final String IS_TRAIT_GROUP_EDITOR = "isTraitGroupEditor"; //$NON-NLS-1$

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    IEditorPart editor = (IEditorPart) receiver;
    if (IS_TRAIT_GROUP_EDITOR.equals(property)) {
      return editor instanceof TraitGroupEditor;
    }
    throw new UnreachableCodeReachedException("Tester was declared for untested property."); //$NON-NLS-1$
  }
}