package net.sf.anathema.map.view.data;

import gis.gisterm.gcore.GenericLayer;
import de.disy.gis.core.gisgraphics.GisGraphicsObjectList;
import de.disy.gis.gisterm.map.layer.edit.ILayerGraphicsEditStrategy;
import de.disy.gis.gisterm.map.layer.edit.ILayerGraphicsEditStrategyFactory;
import de.disy.gisterm.pro.layer.dialog.panels.classification.pointsignature.IImagedSignatureEditConfiguration;
import de.disy.gisterm.pro.sketchlayer.edit.SketchLayerGraphicsEditStrategy;

public final class SketchLayerEditStrategyFactory implements
		ILayerGraphicsEditStrategyFactory {

	public boolean isApplicableTo(final GenericLayer layer) {
		return layer instanceof AnathemaSketchLayer;
	}

	public ILayerGraphicsEditStrategy createFor(final GenericLayer layer) {
		final AnathemaSketchLayer sketchLayer = (AnathemaSketchLayer) layer;
		IImagedSignatureEditConfiguration configuration = IImagedSignatureEditConfiguration.WITHOUT_IMAGES;
		GisGraphicsObjectList objectList = sketchLayer.getGraphicsObjectList();
		return new SketchLayerGraphicsEditStrategy(objectList, configuration);
	}
}
