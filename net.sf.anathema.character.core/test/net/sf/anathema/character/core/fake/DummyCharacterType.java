package net.sf.anathema.character.core.fake;

import net.sf.anathema.character.core.character.ICharacterType;

import org.eclipse.jface.resource.ImageDescriptor;

@SuppressWarnings("nls")
public final class DummyCharacterType implements ICharacterType {
  public static final String DEFAULT_TYPE = "DummyCharacterType";
  public String typeId = DEFAULT_TYPE;

  @Override
  public ImageDescriptor getImageDescriptor() {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public String getTraitImageId() {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public String getId() {
    return typeId;
  }
}