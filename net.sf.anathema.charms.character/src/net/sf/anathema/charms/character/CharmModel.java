package net.sf.anathema.charms.character;

import net.disy.commons.core.model.listener.IChangeListener;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class CharmModel implements ICharmModel {

  @Override
  public void updateToDependencies() {
    // TODO Auto-generated method stub

  }

  @Override
  public void addDirtyListener(IChangeListener changeListener) {
    // TODO Auto-generated method stub

  }

  @Override
  public int getDirtyListenerCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean isDirty() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setClean() {
    // TODO Auto-generated method stub

  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // TODO Auto-generated method stub

  }

  @Override
  public void addChangeListener(IChangeListener arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeChangeListener(IChangeListener arg0) {
    // TODO Auto-generated method stub

  }

}
