// Copyright (c) 2003 by disy Informationssysteme GmbH
package net.sf.anathema.map.view.exchange;

import gis.gisterm.gcore.GenericLayer;
import gis.gisterm.map.FeatureLayer;
import gis.gisterm.map.GenericLegendLayer;
import gis.gisterm.map.featureview.dialog.FeatureViewDialogConfiguration;
import gis.services.persistence.themes.SerializableThemeFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import net.disy.commons.core.provider.IProvider;
import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.action.ActionWidgetFactory;
import net.disy.commons.swing.action.GroupedMenuItem;
import net.disy.commons.swing.action.SmartToggleAction;
import de.disy.cadenza.core.extensions.IExtensionProvider;
import de.disy.cadenza.core.util.IRegistry;
import de.disy.gis.gisterm.gfx.display.IMapDisplayContext;
import de.disy.gis.gisterm.gfx.display.IMapDisplayContextProvider;
import de.disy.gis.gisterm.map.IMapModel;
import de.disy.gis.gisterm.map.layer.feature.label.FeatureLabelMode;
import de.disy.gis.gisterm.map.layer.feature.util.FeatureLayerUtilities;
import de.disy.gis.gisterm.map.scale.IScaleRange;
import de.disy.gis.gisterm.map.scale.ScaleRangeUtilities;
import de.disy.gis.gisterm.map.selection.IThemeSelectionModel;
import de.disy.gis.gisterm.map.theme.GroupTheme;
import de.disy.gis.gisterm.map.theme.ITheme;
import de.disy.gis.gisterm.map.theme.IThemeVisitor;
import de.disy.gis.gisterm.map.theme.LayerTheme;
import de.disy.gis.gisterm.map.theme.ThemeLayerId;
import de.disy.gis.gisterm.map.theme.grouped.IGroupThemeAnnex;
import de.disy.gis.gisterm.module.FeatureViewActionFactoryRegistry;
import de.disy.gis.gisterm.pro.actions.persistence.SaveLayerAction;
import de.disy.gis.gisterm.pro.actions.persistence.SaveThemeRepositoryAction;
import de.disy.gis.gisterm.pro.basic.ThemeScaleUtilities;
import de.disy.gis.gisterm.pro.customization.GisTermProCustomizations;
import de.disy.gisterm.pro.layer.popup.FeatureLayerLabelMenuFactory;
import de.disy.gisterm.pro.layer.popup.IFeatureLayerLabelConfiguration;
import de.disy.gisterm.pro.layer.popup.ShowThemeErrorAction;
import de.disy.gisterm.pro.layer.popup.SpecializedLayerMenuFactory;
import de.disy.gisterm.pro.layer.popup.action.GroupLayersAction;
import de.disy.gisterm.pro.layer.popup.action.LowerThemeAction;
import de.disy.gisterm.pro.layer.popup.action.RaiseThemeAction;
import de.disy.gisterm.pro.layer.popup.action.RemoveLayersAction;
import de.disy.gisterm.pro.layer.popup.action.ThemeMoveIconResources;
import de.disy.gisterm.pro.layer.popup.action.ThemePropertiesAction;
import de.disy.gisterm.pro.layer.popup.action.ThemeToBottomAction;
import de.disy.gisterm.pro.layer.popup.action.ThemeToTopAction;
import de.disy.gisterm.pro.layer.popup.action.UnGroupLayersAction;
import de.disy.gisterm.pro.layer.popup.action.ZoomToScaleRangeAction;
import de.disy.gisterm.pro.layer.popup.action.ZoomToThemeExtentAction;
import de.disy.gisterm.pro.layer.popup.factory.ISpecializedMenuFactory;
import de.disy.gisterm.pro.layer.popup.feature.FeatureLayerSelectionMenuBuilder;
import de.disy.gisterm.pro.layer.popup.group.GroupThemeActionRegistryFactory;
import de.disy.gisterm.pro.layer.popup.group.IGroupThemeAnnexActionFactory;
import de.disy.gisterm.pro.map.popupmenu.IMapMenuItemFactory;
import de.disy.gisterm.pro.map.popupmenu.IMapSelectionContext;
import de.disy.gisterm.pro.menu.IGisTermMenuFactoryProvider;
import de.disy.gisterm.pro.toolbar.AttributeSearchAction;

// NOT_PUBLISHED
// TODO Replace by LayerPanelPopupFactory from GISterm
public class LayerPanelPopupFactory {

	private final ITheme theme;
	private final IMapModel mapModel;
	private final GisTermProCustomizations customizations;
	private final IGisTermMenuFactoryProvider menuFactoryRegistry;
	private final String nullValueDisplayText;
	private final FeatureViewActionFactoryRegistry featureViewActionFactoryRegistry;
	private final IExtensionProvider extensionProvider;
	private final IRegistry<ISpecializedMenuFactory> specializedMenuFactoryRegistry;

	public LayerPanelPopupFactory(
			final ITheme theme,
			final IMapModel mapModel,
			final GisTermProCustomizations customizations,
			final IGisTermMenuFactoryProvider menuFactoryRegistry,
			final IRegistry<ISpecializedMenuFactory> specializedMenuFactoryRegistry,
			final FeatureViewActionFactoryRegistry featureViewActionFactoryRegistry,
			final String nullValueDisplayText,
			final IExtensionProvider extensionProvider) {
		Ensure.ensureArgumentNotNull(theme);
		Ensure.ensureArgumentNotNull(mapModel);
		Ensure.ensureArgumentNotNull(menuFactoryRegistry);
		Ensure.ensureArgumentNotNull(customizations);
		Ensure.ensureArgumentNotNull(featureViewActionFactoryRegistry);
		Ensure.ensureArgumentNotNull(nullValueDisplayText);
		Ensure.ensureArgumentNotNull(extensionProvider);
		Ensure.ensureArgumentNotNull(specializedMenuFactoryRegistry);
		this.menuFactoryRegistry = menuFactoryRegistry;
		this.specializedMenuFactoryRegistry = specializedMenuFactoryRegistry;
		this.theme = theme;
		this.mapModel = mapModel;
		this.customizations = customizations;
		this.featureViewActionFactoryRegistry = featureViewActionFactoryRegistry;
		this.nullValueDisplayText = nullValueDisplayText;
		this.extensionProvider = extensionProvider;
	}

	public JPopupMenu createPopupMenu(final IMapSelectionContext context) {
		final IThemeSelectionModel selectionModel = mapModel
				.getSelectionModel();
		final ITheme[] selectedThemes = selectionModel.getSelectedThemes();
		Ensure
				.ensureArgumentTrue(
						"At least one theme must be selected.", selectedThemes.length > 0); //$NON-NLS-1$
		if (selectedThemes.length == 1) {
			final ITheme firstTheme = selectedThemes[0];
			class CreatePopupVisitor implements IThemeVisitor {
				public JPopupMenu popup;

				public void visitLayerTheme(final LayerTheme visitedTheme) {
					popup = createLayerPopup(visitedTheme, context);
				}

				public void visitGroupTheme(final GroupTheme visitedTheme) {
					popup = createGroupLayerPopup(visitedTheme, context);
				}
			}
			final CreatePopupVisitor visitor = new CreatePopupVisitor();
			firstTheme.accept(visitor);
			return visitor.popup;
		}
		return createMultipleLayerPopup(selectedThemes, context);
	}

	private JPopupMenu createGroupLayerPopup(final GroupTheme groupTheme,
			final IMapSelectionContext context) {
		final JPopupMenu popupMenu = new JPopupMenu();
		addShowErrorActionIfNecessary(popupMenu, groupTheme);
		popupMenu.add(new ZoomToThemeExtentAction(mapModel, groupTheme));
		addZoomToScaleActionIfNecessary(popupMenu, context, groupTheme);
		popupMenu.add(createPositionSubMenu());
		popupMenu.add(new RemoveLayersAction(groupTheme, mapModel));
		final UnGroupLayersAction unGroupLayersAction = new UnGroupLayersAction(
				groupTheme, mapModel);
		popupMenu.add(unGroupLayersAction);
		unGroupLayersAction.setEnabled(customizations
				.isThemeGroupingActionsEnabled());
		addGroupExtensionActions(popupMenu, groupTheme.getAnnexes());
		popupMenu.addSeparator();
		addSaveLayerActions(popupMenu, groupTheme);
		popupMenu.addSeparator();
		addLabellingMenu(popupMenu, groupTheme);
		addMenusFromFactories(popupMenu, context);
		addAttributeSearchActionIfRequired(popupMenu, context,
				new ITheme[] { groupTheme });
		popupMenu.addSeparator();
		popupMenu.add(new ThemePropertiesAction(mapModel, groupTheme));
		return popupMenu;
	}

	private void addLabellingMenu(final JPopupMenu popupMenu,
			final GroupTheme groupTheme) {
		final GenericLayer[] allLayers = groupTheme.getAllLayers();
		final ArrayList<FeatureLayer> featureLayers = new ArrayList<FeatureLayer>();
		for (final GenericLayer layer : allLayers) {
			if (layer instanceof FeatureLayer) {
				featureLayers.add((FeatureLayer) layer);
			}
		}
		if (featureLayers.size() == 0) {
			return;
		}
		final JMenu labelMenu = createLabelMenu(featureLayers
				.toArray(new FeatureLayer[featureLayers.size()]));
		popupMenu.add(labelMenu);
	}

	private JMenu createLabelMenu(final FeatureLayer[] layers) {
		return new FeatureLayerLabelMenuFactory(
				new IFeatureLayerLabelConfiguration() {
					@Override
					public void setLabelMode(final FeatureLabelMode itemMode) {
						for (final FeatureLayer featureLayer : layers) {
							featureLayer.setFeatureLabelMode(itemMode);
						}
					}

					@Override
					public FeatureLabelMode getLabelMode() {
						return layers[0].getLabelConfiguration().getLabelMode();
					}
				}).createLabelMenu();
	}

	private void addGroupExtensionActions(final JPopupMenu popupMenu,
			final IGroupThemeAnnex[] extensions) {
		final List<Action> actions = new ArrayList<Action>();
		for (int extensionIndex = 0; extensionIndex < extensions.length; extensionIndex++) {
			final IGroupThemeAnnexActionFactory factory = GroupThemeActionRegistryFactory
					.getInstance();
			Collections.addAll(actions, factory
					.getActions(extensions[extensionIndex]));
		}
		if (actions.size() > 0) {
			popupMenu.addSeparator();
		}
		for (int index = 0; index < actions.size(); index++) {
			final Action action = actions.get(index);
			addAction(popupMenu, action);
		}
	}

	private void addAction(final JPopupMenu popupMenu, final Action action) {
		if (action instanceof SmartToggleAction) {
			popupMenu.add(ActionWidgetFactory
					.createToggleMenuItem((SmartToggleAction) action));
		} else {
			popupMenu.add(action);
		}
	}

	private void addShowErrorActionIfNecessary(final JPopupMenu popupMenu,
			final ITheme selectedTheme) {
		final Action action = ShowThemeErrorAction
				.createShowThemeErrorActionIfNecessary(selectedTheme);
		if (action != null) {
			popupMenu.add(action);
			popupMenu.addSeparator();
		}
	}

	private void addZoomToScaleActionIfNecessary(final JPopupMenu popupMenu,
			final IMapSelectionContext context, final ITheme selectedTheme) {
		final IScaleRange[] scaleRanges = ThemeScaleUtilities
				.getScaleRanges(selectedTheme);
		if (context.getDisplayContext().getDisplay() != null
				&& !ScaleRangeUtilities.contains(scaleRanges, mapModel
						.getViewProperties().getScale())) {
			popupMenu.add(new ZoomToScaleRangeAction(context
					.getDisplayContext().getDisplay(), scaleRanges));
		}
	}

	private JPopupMenu createMultipleLayerPopup(final ITheme[] themes,
			final IMapSelectionContext context) {
		final JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.add(new ZoomToThemeExtentAction(mapModel, themes));
		popupMenu.add(new RemoveLayersAction(themes, mapModel));
		final GroupLayersAction groupLayersAction = new GroupLayersAction(
				themes, mapModel);
		groupLayersAction.setEnabled(customizations
				.isThemeGroupingActionsEnabled());
		popupMenu.add(groupLayersAction);
		popupMenu.addSeparator();
		addMulitpleFeatureLayerLabelMenu(popupMenu, themes);
		addMenusFromFactories(popupMenu, context);
		addAttributeSearchActionIfRequired(popupMenu, context, themes);
		return popupMenu;
	}

	private void addMulitpleFeatureLayerLabelMenu(final JPopupMenu popupMenu,
			final ITheme[] themes) {
		if (!containsFeatureLayer(themes)) {
			return;
		}
		final JMenu labelMenu = createLabelMenu(FeatureLayerUtilities
				.getAllFeatureLayers(themes));
		popupMenu.add(labelMenu);
	}

	private void addAttributeSearchActionIfRequired(final JPopupMenu menu,
			final IMapSelectionContext context, final ITheme[] themes) {
		if (!containsFeatureLayer(themes)) {
			return;
		}
		final FeatureLayerSelectionMenuBuilder builder = new FeatureLayerSelectionMenuBuilder();
		builder.addDefaultSelectionModelActions(FeatureLayerUtilities
				.getAllSelectionModels(themes));
		menu.add(builder.createMenu());
		final IProvider<IMapModel> mapModelProvider = new IProvider<IMapModel>() {
			public IMapModel getObject() {
				return context.getMapModel();
			}
		};
		final FeatureViewDialogConfiguration configuration = new FeatureViewDialogConfiguration(
				featureViewActionFactoryRegistry.getActionFactories(),
				nullValueDisplayText, extensionProvider);
		menu.add(new AttributeSearchAction(new IMapDisplayContextProvider() {
			public IMapDisplayContext getMapDisplayContext() {
				return context.getDisplayContext();
			}
		}, mapModelProvider, configuration));
	}

	private boolean containsFeatureLayer(final ITheme[] themes) {
		final FeatureLayer[] allFeatureLayers = FeatureLayerUtilities
				.getAllFeatureLayers(themes);
		return allFeatureLayers.length > 0;
	}

	private JPopupMenu createLayerPopup(final LayerTheme layerTheme,
			final IMapSelectionContext context) {
		final GenericLegendLayer layer = (GenericLegendLayer) layerTheme
				.getLayer();
		if (!layer.isSpecializedMenuInitialized()) {
			layer.defineSpecializedMenu(context);
		}
		final JPopupMenu popup = new JPopupMenu();
		addShowErrorActionIfNecessary(popup, layerTheme);
		popup.add(new ZoomToThemeExtentAction(mapModel, layerTheme));
		addZoomToScaleActionIfNecessary(popup, context, layerTheme);
		popup.add(createPositionSubMenu());
		popup.add(new RemoveLayersAction(layerTheme, mapModel));
		addSaveLayerActions(popup, layerTheme);
		final ThemeLayerId themeLayerId = new ThemeLayerId(layerTheme.getId(),
				layer.getId());
		addMenusFromFactories(popup, context);
		addSpecializedLayerMenu(popup, layer, themeLayerId, context);
		return popup;
	}

	private void addMenusFromFactories(final JPopupMenu popup,
			final IMapSelectionContext context) {
		final IMapMenuItemFactory[] menuItemFactories = menuFactoryRegistry
				.getLegendContextMenuItemFactories();
		for (final IMapMenuItemFactory factory : menuItemFactories) {
			final GroupedMenuItem[] items = factory.createMenuItems(context);
			for (final GroupedMenuItem item : items) {
				popup.add(item.getMenuItem());
			}
		}
	}

	private JMenu createPositionSubMenu() {
		final JMenu positionMenu = new JMenu("Position ändern");
		positionMenu.setIcon(ThemeMoveIconResources.THEMES);
		positionMenu.enableInputMethods(false);
		positionMenu.add(new ThemeToTopAction(mapModel, theme));
		positionMenu.addSeparator();
		positionMenu.add(new RaiseThemeAction(mapModel, theme));
		positionMenu.add(new LowerThemeAction(mapModel, theme));
		positionMenu.addSeparator();
		positionMenu.add(new ThemeToBottomAction(mapModel, theme));
		return positionMenu;
	}

	private void addSpecializedLayerMenu(final JPopupMenu popup,
			final GenericLegendLayer layer, final ThemeLayerId themeLayerId,
			final IMapSelectionContext context) {
		final SpecializedLayerMenuFactory menuFactory = new SpecializedLayerMenuFactory(
				specializedMenuFactoryRegistry);
		final JMenuItem[] specializedItems = menuFactory.addSpecialActions(
				layer, themeLayerId, context);
		for (int i = 0; i < specializedItems.length; i++) {
			addPopupItem(popup, specializedItems[i]);
		}
		final List<?> userDefinedItems = layer.getSpecializedMenu();
		if (userDefinedItems != null) {
			for (int i = 0; i < userDefinedItems.size(); i++) {
				addPopupItem(popup, userDefinedItems.get(i));
			}
		}
	}

	private void addPopupItem(final JPopupMenu popup, final Object nextItem) {
		if (nextItem instanceof JMenuItem) {
			popup.add((JMenuItem) nextItem);
		} else {
			popup.addSeparator();
		}
	}

	private void addSaveLayerActions(final JPopupMenu popup, final ITheme theme) {
		if (!SerializableThemeFilter.containsUnsaveableLayers(theme)) {
			List<Action> actions = new ArrayList<Action>();
			if (false) {
				actions.add(new SaveLayerAction(theme));
			}
			if (false) {
				actions.add(new SaveThemeRepositoryAction(theme));
			}
			if (actions.size() > 0) {
				popup.addSeparator();
				for (Action action : actions) {
					popup.add(action);
				}
			}
		}
	}
}