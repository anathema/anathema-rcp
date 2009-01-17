package net.sf.anathema.charms.extension;

import java.util.List;

import net.sf.anathema.charms.data.duration.PrimitiveDurationDto;

public class PrimitiveDurationFactory {

  public PrimitiveDurationDto addPrimitiveDurationTo(List<PrimitiveDurationDto> list) {
    PrimitiveDurationDto primitiveDuration = new PrimitiveDurationDto();
    list.add(primitiveDuration);
    return primitiveDuration;
  }
}