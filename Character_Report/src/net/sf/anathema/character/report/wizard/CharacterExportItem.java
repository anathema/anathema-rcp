package net.sf.anathema.character.report.wizard;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.report.internal.wizard.Character;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;

public class CharacterExportItem implements net.sf.anathema.basics.pdfexport.writer.IExportItem<ICharacter> {

  private final ICharacterId characterId;

  public CharacterExportItem(ICharacterId characterId) {
    this.characterId = characterId;
  }

  @Override
  public ICharacter createItem() {
    return new Character(
        characterId,
        ModelCache.getInstance(),
        new CharacterTemplateProvider(),
        new CharacterTypeFinder());
  }

  @Override
  public String getPrintName() {
    IContainer container = (IContainer) characterId.getAdapter(IContainer.class);
    return new CharacterDisplayNameProvider(container).getDisplayName();
  }

  @Override
  public boolean isFor(IResource resource) {
    IContainer characterContainer = (IContainer) characterId.getAdapter(IContainer.class);
    return resource.getParent().equals(characterContainer);
  }
}