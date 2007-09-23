package net.sf.anathema.character.trait.groupeditor;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.Logger;

public class TraitGroupEditorDecorationFactory {

  private final IPluginExtension[] extensions;

  public TraitGroupEditorDecorationFactory() {
    this(new EclipseExtensionProvider().getExtensions("net.sf.anathema.editor.decoration.trait")); //$NON-NLS-1$
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
          new Logger("net.sf.anathema.character.trait").error(Messages.TraitGroupEditorDecorationFactory_ErrorMessageLoadingDecoration, e); //$NON-NLS-1$
        }
      }
    }
    return decorations;
  }
}