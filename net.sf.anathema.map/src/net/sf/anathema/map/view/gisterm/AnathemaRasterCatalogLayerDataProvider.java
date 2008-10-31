package net.sf.anathema.map.view.gisterm;

import gis.gisterm.map.layer.datasource.RasterCatalogLayerDataProvider;
import de.disy.tools.imaging.provider.IImageRepresentationReader;

public class AnathemaRasterCatalogLayerDataProvider extends
		RasterCatalogLayerDataProvider {

	public AnathemaRasterCatalogLayerDataProvider(
			IImageRepresentationReader reader) {
		super(reader);
	}
}