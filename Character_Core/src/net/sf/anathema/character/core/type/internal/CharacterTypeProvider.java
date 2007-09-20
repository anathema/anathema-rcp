package net.sf.anathema.character.core.type.internal;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.CollectionUtilities;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.type.ICharacterType;

public class CharacterTypeProvider {

  private final IPluginExtension[] extensions;
  private List<ICharacterType> characterTypes;

  public CharacterTypeProvider() {
    this(new EclipseExtensionProvider().getExtensions("net.sf.anathema.character.types")); //$NON-NLS-1$
  }

  public CharacterTypeProvider(IPluginExtension... extensions) {
    this.extensions = extensions;
  }

  public ICharacterType getCharacterTypeById(final String id) {
    return CollectionUtilities.find(getCharacterTypes(), new IPredicate<ICharacterType>() {
      @Override
      public boolean evaluate(ICharacterType type) {
        return id.equals(type.getId());
      }
    });
  }

  private synchronized List<ICharacterType> getCharacterTypes() {
    if (characterTypes == null) {
      characterTypes = readCharacterTypes();
    }
    return characterTypes;
  }

  private List<ICharacterType> readCharacterTypes() {
    List<ICharacterType> readTypes = new ArrayList<ICharacterType>();
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        readTypes.add(new CharacterType(element));
      }
    }
    return readTypes;
  }
}