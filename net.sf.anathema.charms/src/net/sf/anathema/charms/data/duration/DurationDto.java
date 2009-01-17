package net.sf.anathema.charms.data.duration;

import java.util.ArrayList;
import java.util.List;

public class DurationDto {

  public String keyword;
  public List<PrimitiveDurationDto> additions = new ArrayList<PrimitiveDurationDto>();
  public List<PrimitiveDurationDto> minimums = new ArrayList<PrimitiveDurationDto>();
}