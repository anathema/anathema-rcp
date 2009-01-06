package net.sf.anathema.charms.textencoder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.textreport.encoder.AbstractTextEncoder;
import net.sf.anathema.character.trait.display.DisplayFactoryLookup;
import net.sf.anathema.character.trait.display.IDisplayGroupFactory;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.model.MainTraitModelProvider;
import net.sf.anathema.character.trait.resources.TraitMessages;
import net.sf.anathema.charms.character.model.GenericCharmCollector;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.data.lookup.CharmNamesExtensionPoint;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;

public class GenericCharmTextEncoder extends AbstractTextEncoder {

  @Override
  public Iterable<Element> createParagraphs(ICharacter character) throws DocumentException {
    Collection<String> generics = new GenericCharmCollector().collect(character);
    List<IDisplayTraitGroup<IDisplayTrait>> groups = getDisplayGroups(character);
    ICharmModel charmModel = (ICharmModel) character.getModel(ICharmModel.MODEL_ID);
    CharmNamesExtensionPoint names = new CharmNamesExtensionPoint();
    Phrase genericsParagraph = createTextParagraph(createTextChunk("")); //$NON-NLS-1$
    for (String generic : generics) {
      List<String> traits = getTraits(groups, charmModel, generic);
      if (traits.isEmpty()) {
        continue;
      }
      String charmName = getCharmName(names, generic);
      Chunk title = createTextChunk(MessageFormat.format(
          Messages.GenericCharmTextEncoder_Intro,
          character.getDisplayName(),
          charmName));
      for (String trait : traits) {
        int index = traits.indexOf(trait);
        if (index == traits.size() - 1 && index != 0) {
          title.append(Messages.GenericCharmTextEncoder_FinalConjunction);
        }
        else if (index != 0) {
          title.append(", "); //$NON-NLS-1$
        }
        title.append(trait);
      }
      genericsParagraph.add(title);
      genericsParagraph.add(createTextChunk(".\n")); //$NON-NLS-1$
    }
    return Collections.singletonList((Element) genericsParagraph);
  }

  private String getCharmName(CharmNamesExtensionPoint names, String generic) {
    String charmName = names.getNameFor(new CharmId(generic, "")); //$NON-NLS-1$
    charmName = charmName.replaceAll("\\s+", " "); //$NON-NLS-1$ //$NON-NLS-2$
    return charmName;
  }

  private List<String> getTraits(List<IDisplayTraitGroup<IDisplayTrait>> groups, ICharmModel charmModel, String generic) {
    List<String> traits = new ArrayList<String>();
    for (IDisplayTraitGroup<IDisplayTrait> group : groups) {
      for (IDisplayTrait trait : group) {
        String traitId = trait.getTraitType().getId();
        ICharmId charmId = new CharmId(generic, traitId);
        if (charmModel.isLearned(charmId)) {
          traits.add(new TraitMessages().getNameFor(traitId));
        }
      }
    }
    return traits;
  }

  private List<IDisplayTraitGroup<IDisplayTrait>> getDisplayGroups(ICharacter character) {
    String mainModel = new MainTraitModelProvider().getFor(character.getCharacterType().getId());
    IDisplayGroupFactory factory = new DisplayFactoryLookup().getFor(mainModel);
    return factory.createDisplayTraitGroups(character);
  }
}