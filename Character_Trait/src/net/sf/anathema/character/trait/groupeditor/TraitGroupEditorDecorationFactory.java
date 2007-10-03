package net.sf.anathema.character.trait.groupeditor;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.trait.plugin.CharacterTraitPluginConstants;

public class TraitGroupEditorDecorationFactory {

  private final IPluginExtension[] extensions;

  public TraitGroupEditorDecorationFactory() {
    this(new EclipseExtensionPoint(CharacterTraitPluginConstants.PLUGIN_ID, "editordecorations").getExtensions()); //$NON-NLS-1$
  }

  public TraitGroupEditorDecorationFactory(IPluginExtension... extensions) {
    this.extensions = extensions;
  }

  public Collection<ITraitGroupEditorDecoration> create() {
    Collection<ITraitGroupEditorDecoration> decorations = new ArrayList<ITraitGroupEditorDecoration>();
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        try {
          decorations.add(element.getAttributeAsObject("class", ITraitGroupEditorDecoration.class)); //$NON-NLS-1$
        }
        catch (ExtensionException e) {
          new Logger(CharacterTraitPluginConstants.PLUGIN_ID).error(
              Messages.TraitGroupEditorDecorationFactory_ErrorMessageLoadingDecoration,
              e);
        }
      }
    }
    return decorations;
  }
}