package net.sf.anathema.character.experience.points;

import net.sf.anathema.character.experience.IExperiencePoints;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public final class ExperienceContentProvider implements IStructuredContentProvider {
  @Override
  public Object[] getElements(Object inputElement) {
    IExperiencePoints points = (IExperiencePoints) inputElement;
    return points.getEntries();
  }

  @Override
  public void dispose() {
    // nothing to do
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // nothing to do
  }
}