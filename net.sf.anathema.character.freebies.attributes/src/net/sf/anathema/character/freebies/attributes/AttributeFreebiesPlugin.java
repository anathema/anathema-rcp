package net.sf.anathema.character.freebies.attributes;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.character.core.model.IModelChangeListener;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.attributes.plugin.IAttributeFreebiesConstants;
import net.sf.anathema.character.freebies.attributes.problems.AttributeFreebiesProblemFactory;

import org.osgi.framework.BundleContext;

public class AttributeFreebiesPlugin extends AbstractAnathemaUIPlugin {

  private final List<IModelChangeListener> modelListeners = new ArrayList<IModelChangeListener>();
  public static final String ID = IAttributeFreebiesConstants.PLUGIN_ID;
  private AttributeFreebiesPlugin instance;

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    AttributeFreebiesProblemFactory problemFactory = new AttributeFreebiesProblemFactory();
    problemFactory.addAll(modelListeners);
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
    this.instance = new AttributeFreebiesPlugin();
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