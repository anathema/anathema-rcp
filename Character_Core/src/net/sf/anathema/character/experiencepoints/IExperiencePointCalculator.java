package net.sf.anathema.character.experiencepoints;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IExperiencePointCalculator extends IExecutableExtension {

  public int getPoints();
}