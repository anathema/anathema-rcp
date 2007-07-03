package net.sf.anathema.character.core.repository.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.treecontent.itemtype.AbstractFolderBasedViewElementFactory;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;

import org.eclipse.core.resources.IFolder;

public class CharacterViewElementFactory extends AbstractFolderBasedViewElementFactory {

  @Override
  public List<IViewElement> createViewElements(IViewElement parent) {
    ICharacterTemplateProvider templateProvider = new CharacterTemplateProvider();
    List<IViewElement> elements = new ArrayList<IViewElement>();
    for (IFolder folder : getMembers()) {
      if (templateProvider.isTemplateAvailable(folder)) {
        elements.add(new CharacterViewElement(parent, folder, getItemType().getUntitledName(), templateProvider));
      }
    }
    return elements;
  }
}