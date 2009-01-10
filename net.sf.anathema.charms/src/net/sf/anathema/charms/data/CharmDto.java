package net.sf.anathema.charms.data;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charms.data.cost.CostDto;
import net.sf.anathema.charms.data.duration.DurationDto;

public class CharmDto {

  public String type;
  public final List<String> keywords = new ArrayList<String>();
  public final List<SourceDto> sources = new ArrayList<SourceDto>();
  public final List<CostDto> costs = new ArrayList<CostDto>();
  public final List<DurationDto> durations = new ArrayList<DurationDto>();
}