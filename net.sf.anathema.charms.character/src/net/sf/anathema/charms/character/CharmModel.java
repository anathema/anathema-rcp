package net.sf.anathema.charms.character;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.lib.control.ChangeManagement;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class CharmModel extends AbstractModel implements ICharmModel {

  public static final String ID = "net.sf.anathema.charms.character.modelId";
  
  @Override
  public void addChangeListener(IChangeListener listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    // TODO Auto-generated method stub
  }
}
