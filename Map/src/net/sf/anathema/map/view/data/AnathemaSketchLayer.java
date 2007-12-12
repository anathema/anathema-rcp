package net.sf.anathema.map.view.data;

import de.disy.gis.gisterm.map.layer.sketch.AbstractSketchLayer;
import de.disy.gis.gisterm.map.layer.sketch.ISketchPropertiesFactory;
import de.disy.gis.gisterm.map.layer.sketch.ProtoTypeSketchPropertiesFactory;
import de.disy.gis.gisterm.map.layer.sketch.SketchObjectFactory;

public class AnathemaSketchLayer extends AbstractSketchLayer {

  public AnathemaSketchLayer() {
    ISketchPropertiesFactory propertiesFactory = new ProtoTypeSketchPropertiesFactory(new ImageFileProvider());
    setSketchObjectFactory(new SketchObjectFactory(propertiesFactory));
  }
}