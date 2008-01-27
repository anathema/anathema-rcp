package net.sf.anathema.character.core.repository.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.treecontent.itemtype.AbstractFolderBasedViewElementFactory;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.repository.CharacterViewElement;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IFolder;

public class CharacterViewElementFactory extends AbstractFolderBasedViewElementFactory {

  @Override
  public List<IViewElement> createViewElements(IViewElement parent) {
    ICharacterTemplateProvider templateProvider = new CharacterTemplateProvider();
    List<IViewElement> elements = new ArrayList<IViewElement>();
    for (IFolder folder : getMembers()) {
      CharacterId characterId = new CharacterId(folder);
      if (templateProvider.isTemplateAvailable(characterId)) {
        ICharacterTemplate template = templateProvider.getTemplate(characterId);
        elements.add(new CharacterViewElement(parent, folder, template.getName(), templateProvider));
      }
    }
    return elements;
  }
}