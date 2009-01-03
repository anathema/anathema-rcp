package net.sf.anathema.charms.data;

import java.util.ArrayList;
import java.util.List;

public class CharmDto {

  public String type;
  public final List<String> keywords = new ArrayList<String>();
  public final List<SourceDto> sources = new ArrayList<SourceDto>();
}