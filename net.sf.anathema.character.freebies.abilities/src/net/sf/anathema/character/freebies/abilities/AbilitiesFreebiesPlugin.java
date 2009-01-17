package net.sf.anathema.character.freebies.abilities;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.character.core.model.IModelChangeListener;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.abilities.problem.AbilityFreebiesProblemFactory;

import org.osgi.framework.BundleContext;

public class AbilitiesFreebiesPlugin extends AbstractAnathemaUIPlugin {

  private final List<IModelChangeListener> modelListeners = new ArrayList<IModelChangeListener>();
  public static final String ID = "net.sf.anathema.character.freebies.abilities"; //$NON-NLS-1$
  private AbilitiesFreebiesPlugin instance;

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    AbilityFreebiesProblemFactory problemFactory = new AbilityFreebiesProblemFactory();
    modelListeners.add(problemFactory.createForUnrestrictedFreebies());
    modelListeners.add(problemFactory.createForCheapFreebies());
    modelListeners.add(problemFactory.createFavoredCount());
    for (IModelChangeListener listener : modelListeners) {
      ModelCache.getInstance().addModelChangeListener(listener);
    }
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    for (IModelChangeListener listener : modelListeners) {
      ModelCache.getInstance().removeModelChangeListener(listener);
    }
    modelListeners.clear();
    super.stop(context);
  }

  @Override
  protected void createInstance() {
    this.instance = new AbilitiesFreebiesPlugin();
  }

  @Override
  protected void deleteInstance() {
    this.instance = null;
  }

  @Override
  protected AbstractAnathemaUIPlugin getPluginInstance() {
    return instance;
  }
}