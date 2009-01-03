package net.sf.anathema.charms.tree.entries;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public interface ICharmListBuilder<T> {

  void addCharm(IExtensionElement charmElement);

  List<T> create();
}