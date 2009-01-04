package net.sf.anathema.character.trait.model;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;

public class MainTraitModelProvider implements IMainTraitModelProvider {

  private static final String ATTRIB_MODEL_ID = "modelId"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TYPE = "characterType"; //$NON-NLS-1$
  private static final String POINT_ID = "maintraits"; //$NON-NLS-1$
  private final IPluginExtension[] pluginExtensions;

  public MainTraitModelProvider() {
    this(new EclipseExtensionPoint(CharacterTraitPlugin.PLUGIN_ID, POINT_ID).getExtensions());
  }

  public MainTraitModelProvider(IPluginExtension ... pluginExtensions) {
    this.pluginExtensions = pluginExtensions;
  }

  @Override
  public String getFor(String characterTypeId) {
    for (IPluginExtension extension : pluginExtensions) {
      for (IExtensionElement element : extension.getElements()) {
        if (element.getAttribute(ATTRIB_CHARACTER_TYPE).equals(characterTypeId)) {
          return element.getAttribute(ATTRIB_MODEL_ID);
        }
      }
    }
    return null;
  }
}