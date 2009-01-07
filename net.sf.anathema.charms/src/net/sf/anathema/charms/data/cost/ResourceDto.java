package net.sf.anathema.charms.data.cost;

import java.util.ArrayList;
import java.util.List;

public class ResourceDto {

  public String type;
  public BaseDto baseDto;
  public final List<LinearDto> linearDto = new ArrayList<LinearDto>();
}