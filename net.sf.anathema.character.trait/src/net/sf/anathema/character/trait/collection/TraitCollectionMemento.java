package net.sf.anathema.character.trait.collection;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.trait.TraitMemento;

public class TraitCollectionMemento {

  public final Map<String, TraitMemento> mementosByTraitType = new HashMap<String, TraitMemento>();
}