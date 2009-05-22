package net.sf.anathema.character.abilities.sheet;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.display.DisplayTrait;
import net.sf.anathema.character.trait.display.IDisplayFavorization;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

import com.lowagie.text.pdf.PdfContentByte;

public class CraftsSubsectionEncoder extends AbstractTraitSubsectionEncoder {

  private final String title;
  private final int dotCount;
  private final IDisplayTrait craft;

  public CraftsSubsectionEncoder(
      PdfTraitEncoder traitEncoder,
      String title,
      int dotCount,
      int lineCount,
      IDisplayTrait craft) {
    super(traitEncoder, lineCount);
    this.title = title;
    this.dotCount = dotCount;
    this.craft = craft;
  }

  @Override
  public int encode(PdfContentByte directContent, ICharacter character, Position position, float width) {
    List<IDisplayTrait> displayTraits = getSubTraits(character);
    IDisplayTrait[] traitArray = displayTraits.toArray(new IDisplayTrait[displayTraits.size()]);
    return drawNamedTraitSection(directContent, title, traitArray, position, width, dotCount);
  }

  private List<IDisplayTrait> getSubTraits(ICharacter character) {
    List<IDisplayTrait> displayTraits = new ArrayList<IDisplayTrait>();
    ITraitCollectionModel traitCollection = (ITraitCollectionModel) character.getModel(IAbilitiesPluginConstants.MODEL_ID);
    IExperience experience = (IExperience) character.getModel(IExperience.MODEL_ID);
    String traitId = craft.getTraitType().getId();
    for (IBasicTrait subTrait : traitCollection.getSubTraits(traitId)) {
      displayTraits.add(createSubDisplayTrait(experience, subTrait, craft));
    }
    return displayTraits;
  }

  private static IDisplayTrait createSubDisplayTrait(IExperience experience, IBasicTrait trait, IDisplayTrait parent) {
    IDisplayFavorization favorization = parent.getFavorization();
    int maximalValue = parent.getMaximalValue();
    return new DisplayTrait(favorization, trait, experience, maximalValue);
  }
}