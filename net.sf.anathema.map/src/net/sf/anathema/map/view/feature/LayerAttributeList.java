// Copyright (c) 2006 by disy Informationssysteme GmbH
package net.sf.anathema.map.view.feature;

import de.disy.cadenza.api.result.IColumnAttribute;
import de.disy.cadenza.core.result.ColumnAttribute;
import de.disy.gis.gisterm.map.layer.feature.attribute.ILayerAttributeList;
import de.disy.lib.attribute.AttributeType;

// NOT_PUBLISHED
public class LayerAttributeList implements ILayerAttributeList {

  private final IColumnAttribute[] columnAttributes;

  public LayerAttributeList() {
    columnAttributes = new IColumnAttribute[]{
        new ColumnAttribute("id", "id", AttributeType.INT),
        new ColumnAttribute("name", "name", AttributeType.STRING),
        new ColumnAttribute("comment", "comment", AttributeType.STRING) };
  }

  public IColumnAttribute[] getAllColumnAttributes() {
    return columnAttributes;
  }

  public String[] getAdditionalHiddenAttributeNames() {
    return new String[0];
  }
}