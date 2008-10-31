/**
 * 
 */
package net.sf.anathema.map.view.data;

import gis.gisterm.dialog.maplayer.IMapLayerPropertiesDialog;
import gis.gisterm.map.RasterCatalogLayer;
import gis.gisterm.map.layer.datasource.RasterCatalogLayerDataProvider;
import de.disy.tools.imaging.provider.IImageRepresentationReader;

public final class AnathemaRasterLayer extends RasterCatalogLayer {
	public AnathemaRasterLayer(
			IImageRepresentationReader imagePresentationReader) {
		super(new RasterCatalogLayerDataProvider(imagePresentationReader));
	}

	@Override
	protected void customizePropertiesDialog(IMapLayerPropertiesDialog dialog) {
		AnathemaLayerCustomizer.customizePropertiesDialog(dialog, this);
	}
}