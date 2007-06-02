package net.sf.anathema.character.description;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.SimpleTextualDescription;

public class CharacterDescription implements ICharacterDescription {

  private final ITextualDescription nameDescription = new SimpleTextualDescription();
  private final ITextualDescription periphraseDescription = new SimpleTextualDescription();
  private final ITextualDescription characterization = new SimpleTextualDescription();
  private final ITextualDescription physicalDescription = new SimpleTextualDescription();
  private final ITextualDescription notes = new SimpleTextualDescription();
  private final ITextualDescription player = new SimpleTextualDescription();
  private final ITextualDescription concept = new SimpleTextualDescription();

  public ITextualDescription getName() {
    return nameDescription;
  }

  public ITextualDescription getPlayer() {
    return player;
  }

  public ITextualDescription getPeriphrase() {
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

  private ITextualDescription[] getAllDescriptions() {
    return new ITextualDescription[] {
        nameDescription,
        periphraseDescription,
        characterization,
        physicalDescription,
        notes,
        player,
        concept };
  }

  public ITextualDescription getConcept() {
    return concept;
  }

  public void addOverallChangeListener(IObjectValueChangedListener<String> listener) {
    for (ITextualDescription description : getAllDescriptions()) {
      description.addTextChangedListener(listener);
    }
  }
}