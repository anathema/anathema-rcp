package net.sf.anathema.charms.character.model;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.charms.tree.ICharmId;

public interface ICharmModel extends IModel {

  public static final String MODEL_ID = "net.sf.anathema.charms.character.modelId"; //$NON-NLS-1$

  public boolean isCreationLearned(ICharmId charmId);

  public boolean isExperienceLearned(ICharmId charmId);

  public boolean isLearned(ICharmId charmId);

  public void toggleCreationLearned(ICharmId charmId);

  public void toggleExperiencedLearned(ICharmId charmId);

  public Iterable<ICharmId> getExperienceLearnedCharms();

  public Iterable<ICharmId> getCreationLearnedCharms();
}