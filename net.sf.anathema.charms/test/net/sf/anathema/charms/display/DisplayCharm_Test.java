package net.sf.anathema.charms.display;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.SourceDto;
import net.sf.anathema.charms.data.cost.BaseDto;
import net.sf.anathema.charms.data.cost.CostDto;
import net.sf.anathema.charms.data.cost.ResourceDto;

import org.junit.Before;
import org.junit.Test;

public class DisplayCharm_Test {

  private static final String NOT_AVAILABLE = "N/A";
  private static final String EMPTY = "-";
  private DisplayCharm charm;
  private CharmDto dto;

  @Before
  public void createCharm() throws Exception {
    dto = new CharmDto();
    charm = new DisplayCharm(dto);
  }

  @Test
  public void returnsTypeFromDto() throws Exception {
    dto.type = "expected";
    assertThat(charm.getType(), is("expected"));
  }

  @Test
  public void returnsNAForNullType() throws Exception {
    assertThat(charm.getType(), is(NOT_AVAILABLE));
  }

  @Test
  public void returnsEmptyKeywordsIfNoneGiven() throws Exception {
    assertThat(charm.getKeywords(), is(EMPTY));
  }

  @Test
  public void returnsKeywordsIfPresent() throws Exception {
    dto.keywords.add("Keyword");
    assertThat(charm.getKeywords(), is("Keyword"));
  }

  @Test
  public void returnsEmptySourcesIfNoneGiven() throws Exception {
    assertThat(charm.getAllSources(), is(EMPTY));
  }

  @Test
  public void returnsSourceIfPresent() throws Exception {
    SourceDto source = new SourceDto();
    source.source = "Text";
    dto.sources.add(source);
    assertThat(charm.getAllSources(), is("Text"));
  }

  @Test
  public void addsAdditionToSource() throws Exception {
    SourceDto source = new SourceDto();
    source.source = "Text";
    source.addition = "Addition";
    dto.sources.add(source);
    assertThat(charm.getAllSources(), is("Text, Addition"));
  }

  @Test
  public void returnsEmptyCostIfNoneGiven() throws Exception {
    assertThat(charm.getCost(), is(EMPTY));
  }

  @Test
  public void returnsCostIfPresent() throws Exception {
    CostDto cost = new CostDto();
    ResourceDto resource = new ResourceDto();
    resource.type = "m";
    resource.baseDto = new BaseDto();
    resource.baseDto.amount = 5;
    cost.resources.add(resource);
    dto.costs.add(cost);
    assertThat(charm.getCost(), is("5m"));
  }

  @Test
  public void returnsEmptyDurationIfNoneGiven() throws Exception {
    assertThat(charm.getDuration(), is(EMPTY));
  }
}