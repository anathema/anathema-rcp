package net.sf.anathema.charms.character.model;

import net.sf.anathema.character.core.character.IModel;

public interface ICharmModel extends IModel {

  public static final String MODEL_ID = "net.sf.anathema.charms.character.modelId"; //$NON-NLS-1$

  public boolean isCreationLearned(String charmId);

  public boolean isExperienceLearned(String charmId);

  public boolean isLearned(String charmId);

  public void toggleCreationLearned(String charmId);

  public void toggleExperiencedLearned(String charmId);

  public Iterable<String> getExperienceLearnedCharms();

  public Iterable<String> getCreationLearnedCharms();
}