package net.sf.anathema.charms.character.sheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.charms.character.model.LearnedCharmCollector;
import net.sf.anathema.charms.character.sheet.stats.CharmStats;
import net.sf.anathema.charms.character.sheet.stats.IMagicStats;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.display.DisplayCharm;
import net.sf.anathema.charms.extension.CharmProvidingExtensionPoint;
import net.sf.anathema.charms.tree.ICharmId;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class CharmEncoder extends AbstractExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public String getHeader(ICharacter character) {
    return Messages.CharmEncoder_Title;
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    Set<ICharmId> learnedCharms = new LearnedCharmCollector().collect(character);
    List<IMagicStats> stats = createPrintStats(learnedCharms, character);
    new MagicTableEncoder(context.getBaseFont(), stats).encodeTable(directContent, bounds);
  }

  private List<IMagicStats> createPrintStats(Collection<ICharmId> learnedCharms, ICharacter character) {
    final List<IMagicStats> printStats = new ArrayList<IMagicStats>();
    ICharmDataMap extensionPoint = CharmProvidingExtensionPoint.CreateCharmDataMap();
    // Case 357: Generics sollen nur einmal auftauchen
    for (ICharmId id : learnedCharms) {
      CharmDto data = extensionPoint.getData(id);
      DisplayCharm charm = new DisplayCharm(data);
      printStats.add(new CharmStats(id, charm));
    }
    return printStats;
  }
}