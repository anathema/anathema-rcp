package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.core.resources.IFolder;

public interface ITraitGroupEditorInput {

  public IDisplayTraitGroup<IInteractiveTrait> [] createDisplayGroups();

  public IFolder getCharacterFolder();

  public ICharacterId getCharacterId();

  public String getGroupLabel(IDisplayTraitGroup<?> group);

  public String getTraitLabel(IIdentificate traitType);

  public IIntViewImageProvider getImageProvider();

  public ITraitGroup findTraitGroup(IIdentificate traitType);
}