/**
 * 
 */
package net.sf.anathema.map.view.data;

import gis.gisterm.dialog.maplayer.IMapLayerPropertiesDialog;
import de.disy.gis.gisterm.map.layer.sketch.AbstractSketchLayer;
import de.disy.gis.gisterm.map.layer.sketch.ISketchPropertiesFactory;
import de.disy.gis.gisterm.map.layer.sketch.ProtoTypeSketchPropertiesFactory;
import de.disy.gis.gisterm.map.layer.sketch.SketchObjectFactory;

public final class AnathemaSketchLayer extends AbstractSketchLayer {

	public AnathemaSketchLayer() {
		ISketchPropertiesFactory propertiesFactory = new ProtoTypeSketchPropertiesFactory(
				new GifFileProvider());
		setSketchObjectFactory(new SketchObjectFactory(propertiesFactory));
	}

	@Override
	protected void customizePropertiesDialog(IMapLayerPropertiesDialog dialog) {
		AnathemaLayerCustomizer.customizePropertiesDialog(dialog, this);
	}
}