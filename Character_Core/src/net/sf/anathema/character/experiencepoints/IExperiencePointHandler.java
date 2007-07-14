package net.sf.anathema.character.experiencepoints;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IExecutableExtension;

public interface IExperiencePointHandler extends IExecutableExtension {

  public int getPoints(IFolder folder);
}