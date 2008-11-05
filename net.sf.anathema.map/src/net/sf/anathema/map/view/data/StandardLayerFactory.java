package net.sf.anathema.map.view.data;

import gis.gisterm.gcore.GenericLayer;
import gis.gisterm.map.RasterCatalogLayer;

import java.io.File;

import net.disy.commons.core.progress.NullProgressMonitor;
import net.sf.anathema.map.view.feature.AnathemaFeatureLayer;

import de.disy.gis.gisterm.imagecatalog.ImageCatalogQuery;
import de.disy.gis.gisterm.imagecatalog.layer.IImageCatalogLayerCreationStrategy;
import de.disy.gis.gisterm.imagecatalog.layer.IImageCatalogProperties;
import de.disy.gis.gisterm.imagecatalog.layer.ImageCatalogLayerCreationStrategy;
import de.disy.gis.mapdesigner.print.report.NullCancelable;
import de.disy.tools.imaging.provider.IImageRepresentationReader;
import de.disy.tools.imaging.provider.SoftCachingImageRepresentationReader;

public class StandardLayerFactory implements IStandardLayerFactory {

  private final IGisDataDirectory gisDataDirectory;

  public StandardLayerFactory(IGisDataDirectory gisDataDirectory) {
    this.gisDataDirectory = gisDataDirectory;
  }

  public GenericLayer createXeriarRasterLayer() throws LayerCreationException {
    if (!gisDataDirectory.canRead()) {
      return null;
    }
    try {
      File xeriarDirectory = new File(gisDataDirectory.getDirectory(), "raster/Xeriar00/"); //$NON-NLS-1$
      if (!xeriarDirectory.exists()) {
        // TODO Fehlerhandling
        System.err.println("No xeriar layer found at " + xeriarDirectory.getCanonicalPath());
        return null;
      }
      File catalogFile = new File(xeriarDirectory, "catalog.dbf"); //$NON-NLS-1$
      return createXeriar(catalogFile);
    }
    catch (Exception e) {
      throw new LayerCreationException("An error occured loading the Xeriar raster layer.", e); //$NON-NLS-1$
    }
  }

  private GenericLayer createXeriar(final File dbfFile) throws Exception {
    final IImageRepresentationReader imagePresentationReader = new SoftCachingImageRepresentationReader();
    RasterCatalogLayer catalogLayer = new AnathemaRasterLayer(imagePresentationReader);
    IImageCatalogProperties properties = new XeriarCreationProperties(dbfFile);
    IImageCatalogLayerCreationStrategy layerCreationStrategy = new ImageCatalogLayerCreationStrategy(properties);
    layerCreationStrategy.initialize(new NullProgressMonitor(), new NullCancelable(), imagePresentationReader);
    ImageCatalogQuery query = new ImageCatalogQuery(layerCreationStrategy, null);
    catalogLayer.getRasterCatalogLayerDataProvider().setQuery(query);
    catalogLayer.setName(properties.getCatalogName());
    return catalogLayer;
  }

  public GenericLayer createSketchLayer() {
    return new AnathemaSketchLayer();
  }

  public GenericLayer createFeatureLayer() {
    return new AnathemaFeatureLayer();
  }
}