package net.sf.anathema.character.trait.resources;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;

import org.eclipse.core.runtime.IStatus;

public class TraitMessages {

  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private static final String EXTENSION_ID = "traitname"; //$NON-NLS-1$
  private final EclipseExtensionPoint extensionPoint;

  public TraitMessages() {
    this.extensionPoint = new EclipseExtensionPoint(CharacterTraitPlugin.PLUGIN_ID, EXTENSION_ID);
  }

  public String getNameFor(final String traitId) {
    IExtensionElement element = extensionPoint.getFirst(new IPredicate<IExtensionElement>() {
      @Override
      public boolean evaluate(IExtensionElement candidate) {
        try {
          INameCollection nameCollection = candidate.getAttributeAsObject(ATTRIB_CLASS, INameCollection.class);
          return nameCollection.knowsNameFor(traitId);
        }
        catch (RuntimeException e) {
          throw e;
        }
        catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    });
    if (element == IExtensionElement.NO_ELEMENT) {
      return traitId;
    }
    return getName(traitId, element);
  }

  private String getName(final String trait, IExtensionElement element) {
    try {
      INameCollection nameCollection = element.getAttributeAsObject(ATTRIB_CLASS, INameCollection.class);
      return nameCollection.getName(trait);
    }
    catch (ExtensionException e) {
      CharacterTraitPlugin.getDefaultInstance().log(IStatus.WARNING, Messages.TraitMessages_Error, e);
      return trait;
    }
  }
}