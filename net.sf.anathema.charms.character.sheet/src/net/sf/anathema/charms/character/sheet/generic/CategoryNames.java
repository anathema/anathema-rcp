package net.sf.anathema.charms.character.sheet.generic;

import java.util.Arrays;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.trait.resources.INameCollection;

public class CategoryNames extends UnconfiguredExecutableExtension implements INameCollection {

  private static final String ABILITY = "net.sf.anathema.character.abilities.model.forgenerics"; //$NON-NLS-1$
  private static final String ATTRIBUTE = "net.sf.anathema.character.attributes.model.forgenerics"; //$NON-NLS-1$

  @Override
  public String getName(String id) {
    if (ABILITY.equals(id)) {
      return Messages.CategoryNames_Ability;
    }
    if (ATTRIBUTE.equals(id)) {
      return Messages.CategoryNames_Attribute;
    }
    return id;
  }

  @Override
  public boolean knowsNameFor(String id) {
    return Arrays.asList(ABILITY, ATTRIBUTE).contains(id);
  }
}