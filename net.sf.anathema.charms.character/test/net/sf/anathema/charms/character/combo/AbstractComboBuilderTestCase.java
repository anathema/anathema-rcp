package net.sf.anathema.charms.character.combo;

import java.util.Collections;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.tree.DummyCharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;

public abstract class AbstractComboBuilderTestCase {

  protected Combo combo;
  protected ComboBuilder builder;
  protected DummyCharmDataMap charmDataMap;

  @Before
  public final void createBuilder() throws Exception {
    combo = new Combo();
    charmDataMap = new DummyCharmDataMap();
    this.builder = new ComboBuilder(combo, charmDataMap);
  }

  protected final CharmDto createComboOkCharm(String type) {
    return createCharmData(type, "Combo-Ok");
  }

  protected final CharmDto createComboBasicCharm(String type) {
    return createCharmData(type, "Combo-Basic");
  }

  protected final CharmDto createCharmData(String type, String... keywords) {
    CharmDto charmDto = new CharmDto();
    Collections.addAll(charmDto.keywords, keywords);
    charmDto.type = type;
    return charmDto;
  }

  protected final ICharmId setCharmDataForId(String id, CharmDto data) {
    DummyCharmId charmId = new DummyCharmId(id);
    charmDataMap.dataById.put(charmId, data);
    return charmId;
  }
}