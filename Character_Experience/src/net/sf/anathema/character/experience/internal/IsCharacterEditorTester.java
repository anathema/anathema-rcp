package net.sf.anathema.character.experience.internal;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.core.character.IModelIdentifier;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.ui.IEditorPart;

public class IsCharacterEditorTester extends PropertyTester {

  private static final String IS_CHARACTER_EDITOR = "isCharacterEditor"; //$NON-NLS-1$

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    IEditorPart editor = (IEditorPart) receiver;
    if (IS_CHARACTER_EDITOR.equals(property)) {
      IModelIdentifier modelIdentifier = (IModelIdentifier) editor.getEditorInput().getAdapter(IModelIdentifier.class);
      return modelIdentifier != null;
    }
    throw new UnreachableCodeReachedException("Tester was declared for untested property."); //$NON-NLS-1$
  }
}