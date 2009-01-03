package net.sf.anathema.charms.character.sheet.generic;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.trait.resources.INameCollection;

public class CategoryNames extends AbstractExecutableExtension implements INameCollection {

  public static final String ABILITY = "Ability(ForGenericOnSheet)"; //$NON-NLS-1$
  public static final String ATTRIBUTE = "Attribute(ForGenericOnSheet)"; //$NON-NLS-1$

  @Override
  public String getName(String id) {
    if (id == ABILITY) {
      return Messages.CategoryNames_Ability; 
    }
    return Messages.CategoryNames_Attribute; 
  }
}