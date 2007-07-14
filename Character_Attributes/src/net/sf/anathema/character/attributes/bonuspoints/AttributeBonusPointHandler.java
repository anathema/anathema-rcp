package net.sf.anathema.character.attributes.bonuspoints;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.IPointHandler;

import org.eclipse.core.resources.IFolder;

public class AttributeBonusPointHandler extends AbstractExecutableExtension implements IPointHandler {

  @Override
  public int getPoints(IFolder folder) {
    return 0;
  }
}