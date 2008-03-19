/**
 * 
 */
package net.sf.anathema.map.view.data;

import gis.gisterm.dialog.maplayer.IMapLayerPropertiesDialog;

import java.awt.Component;
import java.io.File;

import net.disy.commons.core.provider.StaticProvider;
import net.disy.commons.swing.filechooser.SmartFileChooser;
import net.disy.commons.swing.filechooser.filefilter.ExtensionFileFilter;
import net.disy.commons.swing.filechooser.result.IFileChooserOpenResult;
import net.sf.anathema.map.view.exchange.MapLayerPropertiesDialogConfigurator;
import de.disy.cadenza.pro.repository.filechooser.IRepositoryFileChooserContext;
import de.disy.gis.gisterm.map.layer.sketch.AbstractSketchLayer;
import de.disy.gis.gisterm.map.layer.sketch.ISketchPropertiesFactory;
import de.disy.gis.gisterm.map.layer.sketch.ProtoTypeSketchPropertiesFactory;
import de.disy.gis.gisterm.map.layer.sketch.SketchObjectFactory;
import de.disy.gis.gisterm.pro.customization.GisTermProCustomizations;
import de.disy.gis.gisterm.pro.facade.map.layer.properties.ILayerPropertiesPanelFactoryProvider;
import de.disy.gis.gisterm.pro.facade.map.layer.properties.LayerPropertiesPanelFactoryRegistry;
import de.disy.lib.gui.filechooser.IFileProvider;
import de.disy.lib.gui.filechooser.configuration.OneFileFilterOpenConfiguration;

public final class AnathemaSketchLayer extends AbstractSketchLayer {

	public static final class GifFileProvider implements IFileProvider {
		public File getFile(Component parent) {
			OneFileFilterOpenConfiguration configuration = new OneFileFilterOpenConfiguration(
					false, ExtensionFileFilter.GIF_FILE_FILTER);
			SmartFileChooser fileChooser = SmartFileChooser.getInstance();
			IFileChooserOpenResult result = fileChooser.performOpenFileChooser(
					parent, configuration);
			if (result.isCanceled()) {
				return null;
			}
			return result.getSelectedFile();
		}
	}

	public AnathemaSketchLayer() {
		ISketchPropertiesFactory propertiesFactory = new ProtoTypeSketchPropertiesFactory(
				new GifFileProvider());
		setSketchObjectFactory(new SketchObjectFactory(propertiesFactory));
	}

	@Override
	protected void customizePropertiesDialog(IMapLayerPropertiesDialog dialog) {
		GisTermProCustomizations customizations = new GisTermProCustomizations();
		ILayerPropertiesPanelFactoryProvider panelProvider = new LayerPropertiesPanelFactoryRegistry();
		new MapLayerPropertiesDialogConfigurator(
				new StaticProvider<IRepositoryFileChooserContext>(null),
				customizations, panelProvider, true).customizePropertiesDialog(
				dialog, this);
	}
}