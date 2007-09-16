package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.core.resources.IFolder;

public interface ITraitGroupEditorInput {

  public IDisplayTraitGroup[] createDisplayGroups();

  public IFolder getCharacterFolder();

  public String getGroupLabel(IDisplayTraitGroup group);

  public String getTraitLabel(IIdentificate traitType);

  public ISurplusIntViewImageProvider getImageProvider();

  public int getPointsNotCoveredByCredit(IIdentificate traitType);
}