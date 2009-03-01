package net.sf.anathema.character.trait.collection;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.trait.IBasicTrait;

import org.eclipse.osgi.util.NLS;

public abstract class AbstractTraitCollection extends AbstractModel implements ITraitCollectionModel {

  @Override
  public final IBasicTrait getTrait(final String id) {
    for (final IBasicTrait trait : getAllTraits()) {
      if (id.equals(trait.getTraitType().getId())) {
        return trait;
      }
    }
    throw new IllegalArgumentException(NLS.bind(Messages.Trait_NotFound_Message, id));
  }

  @Override
  public final IBasicTrait[] getTraits(final String... ids) {
    return ArrayUtilities.transform(ids, IBasicTrait.class, new TraitExtractor(this));
  }

  @Override
  public final boolean contains(final String traitId) {
    for (final IBasicTrait trait : getAllTraits()) {
      if (traitId.equals(trait.getTraitType().getId())) {
        return true;
      }
    }
    return false;
  }
}