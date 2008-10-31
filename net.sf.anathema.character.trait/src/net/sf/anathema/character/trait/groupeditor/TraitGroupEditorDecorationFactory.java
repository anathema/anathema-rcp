package net.sf.anathema.character.trait.groupeditor;

import java.util.Collection;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;

public class TraitGroupEditorDecorationFactory {

  private final IPluginExtension[] extensions;

  public TraitGroupEditorDecorationFactory() {
    this(new EclipseExtensionPoint(CharacterTraitPlugin.PLUGIN_ID, "editordecorations").getExtensions()); //$NON-NLS-1$
  }

  public TraitGroupEditorDecorationFactory(IPluginExtension... extensions) {
    this.extensions = extensions;
  }

  public Collection<ITraitGroupEditorDecoration> create() {
    return new ClassConveyerBelt<ITraitGroupEditorDecoration>(
        CharacterTraitPlugin.PLUGIN_ID,
        ITraitGroupEditorDecoration.class,
        extensions).getAllObjects();
  }
}