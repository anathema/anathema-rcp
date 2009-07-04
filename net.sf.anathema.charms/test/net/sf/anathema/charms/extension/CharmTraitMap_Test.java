package net.sf.anathema.charms.extension;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import net.sf.anathema.charms.traits.CharmTraits;
import net.sf.anathema.charms.traits.IExecutableCharmTraitMap;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class CharmTraitMap_Test {

  private ArrayList<IExecutableCharmTraitMap> mapList;
  private CharmTraitMap charmTraitMap;
  private ICharmId charmId;

  @Before
  public void createMap() throws Exception {
    mapList = new ArrayList<IExecutableCharmTraitMap>();
    charmTraitMap = new CharmTraitMap(mapList);
  }

  @Before
  public void createTraitId() throws Exception {
    charmId = new CharmId("charm", "trait");
  }

  @Test
  public void returnsValueOneTraitsForUnfoundCharmId() throws Exception {
    CharmTraits traits = charmTraitMap.getTraits(charmId);
    assertThat(traits.essenceMinimum, is(1));
    assertThat(traits.primaryMinimum, is(1));
  }

  @Test
  public void asksMapForCharmTraits() throws Exception {
    CharmTraits traits = new CharmTraits();
    mapList.add(createMockMap(traits));
    assertThat(charmTraitMap.getTraits(charmId), is(sameInstance(traits)));
  }

  @Test
  public void returnsValueOfFollowupMaps() throws Exception {
    CharmTraits traits = new CharmTraits();
    mapList.add(createMockMap(null));
    mapList.add(createMockMap(traits));
    assertThat(charmTraitMap.getTraits(charmId), is(sameInstance(traits)));
  }

  private IExecutableCharmTraitMap createMockMap(CharmTraits traits) {
    IExecutableCharmTraitMap mockMap = createMock(IExecutableCharmTraitMap.class);
    expect(mockMap.getTraits(charmId)).andReturn(traits);
    replay(mockMap);
    return mockMap;
  }
}