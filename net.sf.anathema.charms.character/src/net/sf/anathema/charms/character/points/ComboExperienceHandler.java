package net.sf.anathema.charms.character.points;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.charms.character.combo.Combo;
import net.sf.anathema.charms.character.combo.ComboModel;
import net.sf.anathema.charms.character.combo.IComboModel;
import net.sf.anathema.charms.extension.traits.CharmTraitExtensionPoint;
import net.sf.anathema.charms.traits.CharmTraits;
import net.sf.anathema.charms.traits.ICharmTraitMap;
import net.sf.anathema.charms.tree.ICharmId;

public class ComboExperienceHandler extends UnconfiguredExecutableExtension implements IPointHandler {

  private final IModelCollection modelCollection;
  private final ICharmTraitMap traitsMap;

  public ComboExperienceHandler() {
    this(ModelCache.getInstance(), new CharmTraitExtensionPoint());
  }

  public ComboExperienceHandler(IModelCollection modelCollection, ICharmTraitMap traitsMap) {
    this.modelCollection = modelCollection;
    this.traitsMap = traitsMap;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    IComboModel comboModel = ComboModel.getFrom(modelCollection, characterId);
    int experiencePoints = 0;
    for (Combo combo : comboModel.getExperienceLearned()) {
      experiencePoints += getPoints(combo);
    }
    return experiencePoints;
  }

  private int getPoints(Combo combo) {
    int experiencePoints = 0;
    for (ICharmId charmId : combo.charms) {
      CharmTraits traits = traitsMap.getTraits(charmId);
      if (traits != null) {
        experiencePoints += traits.primaryMinimum;
      }
    }
    return experiencePoints;
  }
}