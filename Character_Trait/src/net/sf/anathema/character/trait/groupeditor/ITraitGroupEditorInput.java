package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.core.resources.IFolder;
import org.eclipse.swt.graphics.Image;

public interface ITraitGroupEditorInput {

  public IDisplayTraitGroup[] createDisplayGroups();

  public IFolder getCharacterFolder();

  public Image createPassiveImage();

  public Image createActiveImage();

  public String getGroupLabel(IDisplayTraitGroup group);

  public String getTraitLabel(IIdentificate traitType);
}