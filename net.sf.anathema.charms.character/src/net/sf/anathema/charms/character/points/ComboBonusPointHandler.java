package net.sf.anathema.charms.character.points;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.charms.character.combo.Combo;
import net.sf.anathema.charms.character.combo.ComboModel;
import net.sf.anathema.charms.character.combo.IComboModel;

public class ComboBonusPointHandler extends UnconfiguredExecutableExtension implements IPointHandler {

  private final IModelCollection modelCollection;

  public ComboBonusPointHandler() {
    this(ModelCache.getInstance());
  }

  public ComboBonusPointHandler(IModelCollection modelCollection) {
    this.modelCollection = modelCollection;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    IComboModel comboModel = ComboModel.getFrom(modelCollection, characterId);
    int bonusPoints = 0;
    for (Combo combo : comboModel.getCreationLearned()) {
      bonusPoints += combo.charms.size();
    }
    return bonusPoints;
  }
}