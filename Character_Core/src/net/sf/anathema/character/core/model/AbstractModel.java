package net.sf.anathema.character.core.model;

import net.sf.anathema.lib.control.ChangeManagement;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public abstract class AbstractModel extends ChangeManagement implements IModel{

  @Override
  public final void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}