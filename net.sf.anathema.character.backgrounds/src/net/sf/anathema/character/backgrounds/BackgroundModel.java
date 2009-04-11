package net.sf.anathema.character.backgrounds;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.util.Identificate;

public class BackgroundModel extends TraitCollection implements IBackgroundModel {

  private final GenericControl<IBackgroundModificationListener> modificationListeners = new GenericControl<IBackgroundModificationListener>();

  public BackgroundModel(IBasicTrait... traits) {
    super(traits);
  }

  @Override
  public void addBackground(String background) {
    final BasicTrait trait = new BasicTrait(new Identificate(background));
    addTrait(trait);
    modificationListeners.forAllDo(new IClosure<IBackgroundModificationListener>() {
      @Override
      public void execute(IBackgroundModificationListener listener) {
        listener.traitAdded(trait);
      }
    });
    setDirty(true);
  }

  @Override
  public void addModificationListener(IBackgroundModificationListener listener) {
    modificationListeners.addListener(listener);
  }

  @Override
  public void removeModificationListener(IBackgroundModificationListener listener) {
    modificationListeners.removeListener(listener);
  }
}