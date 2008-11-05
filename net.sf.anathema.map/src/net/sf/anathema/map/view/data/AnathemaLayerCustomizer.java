package net.sf.anathema.map.view.data;

import gis.gisterm.dialog.maplayer.IMapLayerPropertiesDialog;
import gis.gisterm.gcore.GenericLayer;
import gis.services.debug.modules.PlatformlessFeatureLayerBuilderFactory;

import net.disy.commons.core.provider.StaticProvider;

import de.disy.cadenza.pro.repository.filechooser.IRepositoryFileChooserContext;
import de.disy.gis.gisterm.pro.customization.GisTermProCustomizations;
import de.disy.gis.gisterm.pro.facade.map.layer.properties.ILayerPropertiesPanelFactoryProvider;
import de.disy.gis.gisterm.pro.facade.map.layer.properties.LayerPropertiesPanelFactoryRegistry;
import de.disy.gisterm.pro.MapLayerPropertiesDialogConfigurator;
import de.disy.gisterm.pro.layer.dialog.panels.PresentationDialogConfiguration;

public class AnathemaLayerCustomizer {

  public static void customizePropertiesDialog(IMapLayerPropertiesDialog dialog, GenericLayer layer) {
    GisTermProCustomizations customizations = new GisTermProCustomizations();
    ILayerPropertiesPanelFactoryProvider panelProvider = new LayerPropertiesPanelFactoryRegistry();
    StaticProvider<IRepositoryFileChooserContext> chooserContext = new StaticProvider<IRepositoryFileChooserContext>(
        new RepositoryFileChooserContext());
    MapLayerPropertiesDialogConfigurator configurator = new MapLayerPropertiesDialogConfigurator(
        chooserContext,
        customizations,
        panelProvider,
        new PresentationDialogConfiguration(false),
        true,
        new PlatformlessFeatureLayerBuilderFactory());
    configurator.customizePropertiesDialog(dialog, layer);
  }
}