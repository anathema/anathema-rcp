// Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.map.view.exchange;

import gis.gisterm.GisTerm;
import gis.gisterm.dialog.maplayer.IMapLayerPropertiesDialog;
import gis.gisterm.dialog.maplayer.IMapLayerPropertiesDialogConfigurator;
import gis.gisterm.dialog.maplayer.IMapPropertiesDialogPage;
import gis.gisterm.dialog.maplayer.IScaleEditable;
import gis.gisterm.dialog.maplayer.LayerScalePropertiesPanel;
import gis.gisterm.gcore.GenericLayer;
import gis.gisterm.gcore.Layer;
import gis.gisterm.map.FeatureLayer;
import gis.gisterm.map.IOnlyPropertiesPanelFactoryUsingLayer;
import gis.gisterm.map.LegendLayer;
import gis.gisterm.map.RasterCatalogLayer;
import gis.gisterm.map.RasterLayer;
import gis.gisterm.map.featurelayer.IFeatureLayerBuilderFactory;
import gis.gisterm.map.layer.EmptyLayer;
import gis.gisterm.map.layer.GenericFeatureLayer;
import gis.gisterm.map.layer.GenericRasterLayer;
import gis.gisterm.map.layer.IMapLayer;
import gis.services.debug.modules.PlatformlessFeatureLayerBuilderFactory;
import gis.services.persistence.layer.xml.load.special.DefectiveLoadedSpecialLayer;

import net.disy.commons.core.provider.IProvider;
import net.disy.commons.core.util.Ensure;

import de.disy.cadenza.pro.repository.filechooser.IRepositoryFileChooserContext;
import de.disy.gis.gisterm.map.layer.raster.IRasterManagerProvider;
import de.disy.gis.gisterm.map.scale.IScaleRange;
import de.disy.gis.gisterm.pro.customization.GisTermProCustomizations;
import de.disy.gis.gisterm.pro.facade.map.layer.properties.ILayerPropertiesPanelFactoryProvider;
import de.disy.gis.gisterm.pro.facade.map.layer.properties.IMapPropertiesDialogPageFactory;
import de.disy.gisterm.pro.featurelayer.label.FeatureLabelPropertiesPanel;
import de.disy.gisterm.pro.featurelayer.legend.LegendPropertiesPanel;
import de.disy.gisterm.pro.layer.dialog.panels.PresentationDialogConfiguration;
import de.disy.gisterm.pro.layer.dialog.panels.PresentationPanel;
import de.disy.gisterm.pro.layer.dialog.panels.classification.pointsignature.DefaultImagedSignatureEditConfiguration;
import de.disy.gisterm.pro.layer.dialog.panels.classification.pointsignature.IImagedSignatureEditConfiguration;
import de.disy.gisterm.pro.layer.dialog.panels.display.FeatureLayerDisplayPropertiesPanel;
import de.disy.gisterm.pro.layer.dialog.panels.theme.ThemePanel;
import de.disy.gisterm.pro.layer.dialog.raster.ColorPalettePropertiesPanel;

// NOT_PUBLISHED
public class MapLayerPropertiesDialogConfigurator implements IMapLayerPropertiesDialogConfigurator {

  private final GisTermProCustomizations customizations;
  private final ILayerPropertiesPanelFactoryProvider propertiesPanelFactoryProvider;
  private final IProvider<IRepositoryFileChooserContext> fileChooserContextProvider;
  private final boolean isUserEditableScaleRange;

  public MapLayerPropertiesDialogConfigurator(
      final IProvider<IRepositoryFileChooserContext> fileChooserContext,
      final GisTermProCustomizations customizations,
      final ILayerPropertiesPanelFactoryProvider propertiesPanelFactoryProvider,
      final boolean isUserEditableScaleRange) {
    Ensure.ensureArgumentNotNull(customizations);
    Ensure.ensureArgumentNotNull(propertiesPanelFactoryProvider);
    this.isUserEditableScaleRange = isUserEditableScaleRange;
    this.fileChooserContextProvider = fileChooserContext;
    this.propertiesPanelFactoryProvider = propertiesPanelFactoryProvider;
    this.customizations = customizations;
  }

  public void customizePropertiesDialog(final IMapLayerPropertiesDialog dialog, final GenericLayer layer) {
    final IRepositoryFileChooserContext fileChooserContext = fileChooserContextProvider.getObject();
    if (layer instanceof IOnlyPropertiesPanelFactoryUsingLayer) {
      // nothing to do
    }
    else if (layer instanceof GenericFeatureLayer) {
      final GenericFeatureLayer featureLayer = (GenericFeatureLayer) layer;
      addThemePanelIfAllowed(dialog, fileChooserContext, featureLayer);
      dialog.addDialogPage(new LegendPropertiesPanel(featureLayer, customizations));
      dialog.addDialogPage(new LayerScalePropertiesPanel(new LayerScaleEditable(featureLayer, isUserEditableScaleRange)));
    }
    else if (layer instanceof GenericRasterLayer) {
      final GenericRasterLayer rasterLayer = (GenericRasterLayer) layer;
      addThemePanelIfAllowed(dialog, fileChooserContext, rasterLayer);
      dialog.addDialogPage(new LegendPropertiesPanel(rasterLayer, customizations));
      dialog.addDialogPage(new LayerScalePropertiesPanel(new LayerScaleEditable(rasterLayer, isUserEditableScaleRange)));
    }
    else if (layer instanceof FeatureLayer) {
      final FeatureLayer featureLayer = (FeatureLayer) layer;
      addThemePanelIfAllowed(dialog, fileChooserContext, featureLayer);
      final PresentationDialogConfiguration configuration = new PresentationDialogConfiguration(false);
      final PresentationPanel presentationPanel = new PresentationPanel(
          featureLayer.getPresentationEditable(),
          configuration);
      dialog.addPropertiesPanel(presentationPanel);
      dialog.addDialogPage(new FeatureLayerDisplayPropertiesPanel(featureLayer));
      IImagedSignatureEditConfiguration signatureConfiguration = new DefaultImagedSignatureEditConfiguration(
          GisTerm.getModel().getCadenzaModel());
      IFeatureLayerBuilderFactory layerBuilderFactory = new PlatformlessFeatureLayerBuilderFactory();
      dialog.addDialogPage(new FeatureLabelPropertiesPanel(featureLayer, signatureConfiguration, layerBuilderFactory));
      dialog.addDialogPage(new LegendPropertiesPanel(featureLayer, customizations));
      dialog.addDialogPage(new LayerScalePropertiesPanel(new LayerScaleEditable(featureLayer, isUserEditableScaleRange)));
      dialog.setDefaultPanel(presentationPanel);
    }
    else if (layer instanceof RasterCatalogLayer || layer instanceof RasterLayer) {
      final LegendLayer rasterCatalogLayer = (LegendLayer) layer;
      addThemePanelIfAllowed(dialog, fileChooserContext, rasterCatalogLayer);
      dialog.addDialogPage(new LegendPropertiesPanel(rasterCatalogLayer, customizations));
      dialog.addDialogPage(new ColorPalettePropertiesPanel(
          ((IRasterManagerProvider) rasterCatalogLayer).getRasterManager()));
      dialog.addDialogPage(new LayerScalePropertiesPanel(new LayerScaleEditable(
          rasterCatalogLayer,
          isUserEditableScaleRange)));
    }
    else if (layer instanceof EmptyLayer) {
      // nothing to do
    }
    else if (layer instanceof DefectiveLoadedSpecialLayer) {
      // nothing to do
    }
    else if (layer instanceof LegendLayer) {
      final LegendLayer notesLayer = (LegendLayer) layer;
      dialog.addDialogPage(new LegendPropertiesPanel(notesLayer, customizations));
      dialog.addDialogPage(new LayerScalePropertiesPanel(new LayerScaleEditable(notesLayer, isUserEditableScaleRange)));
    }
    else {
      // TODO 23.01.2008 (bartels): prüfen ob eine factory die layer instance unterstützt
      throw new UnsupportedOperationException("Unsupported layer instance: " + layer.getClass().getName());
    }
    final IMapPropertiesDialogPageFactory[] panelFactories = propertiesPanelFactoryProvider.getAllFactories();
    for (final IMapPropertiesDialogPageFactory panelFactory : panelFactories) {
      for (final IMapPropertiesDialogPage page : panelFactory.createDialogPages(layer)) {
        dialog.addDialogPage(page);
      }
    }
  }

  private void addThemePanelIfAllowed(
      final IMapLayerPropertiesDialog dialog,
      final IRepositoryFileChooserContext fileChooserContext,
      final IMapLayer layer) {
    if (customizations.isThemePanelVisible()) {
      dialog.addDialogPage(new ThemePanel(layer, fileChooserContext));
    }
  }

  public final static class LayerScaleEditable implements IScaleEditable {
    private final Layer layer;
    private final boolean isUserEditableScaleRange;

    public LayerScaleEditable(final Layer layer, final boolean isUserEditableScaleRange) {
      Ensure.ensureArgumentNotNull(layer);
      this.layer = layer;
      this.isUserEditableScaleRange = isUserEditableScaleRange;
    }

    public boolean isScaleRangeEditable() {
      return isUserEditableScaleRange && layer.isScaleRangeEditable();
    }

    public IScaleRange getScaleRange() {
      return layer.getScaleRange();
    }

    public void setScaleRange(final IScaleRange scaleRange) {
      layer.setScaleRange(scaleRange);
    }

    public double getBaseScale() {
      return layer.getBaseScale();
    }
  }
}