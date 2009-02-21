package net.sf.anathema.character.trait.validator.extension;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.trait.validator.where.AllWhere;
import net.sf.anathema.character.trait.validator.where.IWhere;
import net.sf.anathema.character.trait.validator.where.WhereNot;

public class ExtensionWhereFactory {
  private static final String TAG_WHERE = "where"; //$NON-NLS-1$
  private static final String TAG_WHERENOT = "wherenot"; //$NON-NLS-1$

  public IWhere createWhereClause(IExtensionElement parent) {
    List<IWhere> allWheres = new ArrayList<IWhere>();
    addWheres(parent, allWheres);
    addWhereNots(parent, allWheres);
    return new AllWhere(allWheres);
  }

  private void addWheres(IExtensionElement parent, List<IWhere> allWheres) {
    for (IExtensionElement whereElement : parent.getElements(TAG_WHERE)) {
      allWheres.add(new ExtensionWhere(whereElement));
    }
  }

  private void addWhereNots(IExtensionElement parent, List<IWhere> allWheres) {
    for (IExtensionElement whereNotElement : parent.getElements(TAG_WHERENOT)) {
      allWheres.add(new WhereNot(new ExtensionWhere(whereNotElement)));
    }
  }
}