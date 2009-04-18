package net.sf.anathema.character.backgrounds;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.character.backgrounds.model.IBackgroundAdditionListener;
import net.sf.anathema.character.backgrounds.model.IBackgroundModel;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.util.Identificate;

public class BackgroundModel extends TraitCollection implements IBackgroundModel {

  private final GenericControl<IBackgroundAdditionListener<IBasicTrait>> modificationListeners = new GenericControl<IBackgroundAdditionListener<IBasicTrait>>();

  public BackgroundModel(IBasicTrait... traits) {
    super(traits);
  }

  @Override
  public IBasicTrait addBackground(String background) {
    final BasicTrait trait = new BasicTrait(new Identificate(background));
    addTrait(trait);
    modificationListeners.forAllDo(new IClosure<IBackgroundAdditionListener<IBasicTrait>>() {
      @Override
      public void execute(IBackgroundAdditionListener<IBasicTrait> listener) {
        listener.traitAdded(trait);
      }
    });
    setDirty(true);
    return trait;
  }

  @Override
  public void addModificationListener(IBackgroundAdditionListener<IBasicTrait> listener) {
    modificationListeners.addListener(listener);
  }

  @Override
  public void removeModificationListener(IBackgroundAdditionListener<IBasicTrait> listener) {
    modificationListeners.removeListener(listener);
  }
}