package net.sf.anathema.character.description;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.lib.control.AggregatedChangeManagement;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.SimpleTextualDescription;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class CharacterDescription extends AggregatedChangeManagement implements ICharacterDescription {

  private final ITextualDescription nameDescription = new SimpleTextualDescription();
  private final ITextualDescription periphraseDescription = new SimpleTextualDescription();
  private final ITextualDescription characterization = new SimpleTextualDescription();
  private final ITextualDescription physicalDescription = new SimpleTextualDescription();
  private final ITextualDescription notes = new SimpleTextualDescription();
  private final ITextualDescription player = new SimpleTextualDescription();
  private final ITextualDescription concept = new SimpleTextualDescription();

  public CharacterDescription() {
    setChangeManagments(
        nameDescription,
        periphraseDescription,
        characterization,
        physicalDescription,
        notes,
        player,
        concept);
  }

  public ITextualDescription getName() {
    return nameDescription;
  }

  public ITextualDescription getPlayer() {
    return player;
  }

  public ITextualDescription getPeriphrasis() {
    return periphraseDescription;
  }

  public ITextualDescription getCharacterization() {
    return characterization;
  }

  public ITextualDescription getPhysicalDescription() {
    return physicalDescription;
  }

  public ITextualDescription getNotes() {
    return notes;
  }

  public ITextualDescription getConcept() {
    return concept;
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }

  @Override
  public void addChangeListener(IChangeListener modelChangeListener) {
    // nothing to do
  }

  @Override
  public void removeChangeListener(IChangeListener modelChangeListener) {
    // nothing to do
  }
}