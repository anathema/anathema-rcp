package net.sf.anathema.charms.character.sheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.character.sheet.stats.CharmStats;
import net.sf.anathema.charms.character.sheet.stats.IMagicStats;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.display.DisplayCharm;
import net.sf.anathema.charms.extension.CharmDataExtensionPoint;
import net.sf.anathema.charms.tree.CharmIdLookup;
import net.sf.anathema.charms.tree.CharmTreeProvider;
import net.sf.anathema.charms.tree.ICharmId;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class CharmEncoder extends AbstractExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public String getHeader(ICharacter character) {
    return "Charms";
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    ICharmModel model = (ICharmModel) character.getModel(ICharmModel.MODEL_ID);
    IExperience experience = (IExperience) character.getModel(IExperience.MODEL_ID);
    Set<String> learnedCharms = new HashSet<String>();
    addCharms(learnedCharms, model.getCreationLearnedCharms());
    if (experience.isExperienced()) {
      addCharms(learnedCharms, model.getExperienceLearnedCharms());
    }
    List<IMagicStats> magic = collectPrintMagic(learnedCharms);
    new MagicTableEncoder(context.getBaseFont(), magic).encodeTable(directContent, bounds);
  }

  private List<IMagicStats> collectPrintMagic(Collection<String> learnedCharms) {
    final List<IMagicStats> printStats = new ArrayList<IMagicStats>();
    CharmDataExtensionPoint extensionPoint = new CharmDataExtensionPoint();
    CharmIdLookup lookup = new CharmIdLookup(CharmTreeProvider.Create());
    for (String charmId : learnedCharms) {
      ICharmId id = lookup.getCharmId(charmId);
      CharmDto data = extensionPoint.getData(id);
      DisplayCharm charm = new DisplayCharm(data);
      printStats.add(new CharmStats(id, charm));
    }
    return printStats;
  }

  private void addCharms(Collection<String> learnedCharms, Iterable<String> charms) {
    for (String id : charms) {
      learnedCharms.add(id);
    }
  }
}