package net.sf.anathema.charms.tree;

public class TreeDto {

  public static TreeDto FromIdAndName(String id, String name) {
    TreeDto dto = new TreeDto();
    dto.id = id;
    dto.name = name;
    return dto;
  }

  public String id;
  public String name;
}