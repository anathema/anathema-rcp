package net.sf.anathema.character.core.repository;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.treecontent.itemtype.AbstractFolderBasedViewElementFactory;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IFolder;

public class CharacterViewElementFactory extends AbstractFolderBasedViewElementFactory {

  @Override
  public List<IViewElement> createViewElements(IViewElement parent) {
    List<IViewElement> elements = new ArrayList<IViewElement>();
    for (IFolder folder : getMembers()) {
      elements.add(new CharacterViewElement(parent, folder, getItemType().getUntitledName()));
    }
    return elements;
  }
}