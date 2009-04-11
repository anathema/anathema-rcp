package net.sf.anathema.character.backgrounds;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.core.editors.AbstractCharacterModelEditorPart;
import net.sf.anathema.character.core.traitview.IValueContainer;
import net.sf.anathema.character.core.traitview.IntDisplayArea;
import net.sf.anathema.character.core.traitview.IntValuePaintListener;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.display.IntViewImageProvider;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class BackgroundEditor extends AbstractCharacterModelEditorPart<IBackgroundModel> {

  private static final class TextChangingFocusListener implements FocusListener {

    private final Text textfield;

    public TextChangingFocusListener(Text textfield) {
      this.textfield = textfield;
    }

    @Override
    public void focusGained(FocusEvent e) {
      if (textfield.getText().equals(DEFAULT_TEXT)) {
        textfield.setText(EMPTY_TEXT);
      }
    }

    @Override
    public void focusLost(FocusEvent e) {
      if (textfield.getText().equals(EMPTY_TEXT)) {
        textfield.setText(DEFAULT_TEXT);
      }
    }
  }

  private static final String EMPTY_TEXT = "";
  private static final String DEFAULT_TEXT = "Type a background name and press 'Enter'";

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      private Text entry;

      @Override
      public void createPartControl(final Composite parent) {
        final BackgroundEditorInput editorInput = (BackgroundEditorInput) getEditorInput();
        final FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        Composite container = createParentContainer(parent, toolkit);
        createBackgroundEntry(editorInput, toolkit, container);
        createBackgroundTable(editorInput, toolkit, container);
      }

      private Composite createParentContainer(final Composite parent, final FormToolkit toolkit) {
        Form form = toolkit.createForm(parent);
        toolkit.decorateFormHeading(form);
        form.setText(getEditorInput().getName());
        final Composite container = form.getBody();
        container.setLayout(new GridLayout(1, false));
        return container;
      }

      private void createBackgroundEntry(
          final BackgroundEditorInput editorInput,
          final FormToolkit toolkit,
          final Composite container) {
        entry = toolkit.createText(container, DEFAULT_TEXT);
        entry.addSelectionListener(new SelectionListener() {
          @Override
          public void widgetDefaultSelected(SelectionEvent e) {
            editorInput.getItem().addBackground(entry.getText());
          }

          @Override
          public void widgetSelected(SelectionEvent e) {
            // nothing
          }
        });
        entry.addFocusListener(new TextChangingFocusListener(entry));
      }

      private void createBackgroundTable(
          final BackgroundEditorInput editorInput,
          final FormToolkit toolkit,
          final Composite container) {
        final Table table = toolkit.createTable(container, SWT.BORDER | SWT.SINGLE);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        table.addListener(SWT.MeasureItem, new Listener() {
          public void handleEvent(Event event) {
            event.width = getEntryWidth(table);
          }
        });
        table.addListener(SWT.PaintItem, new Listener() {
          public void handleEvent(final Event event) {
            IValueContainer redrawable = new IValueContainer() {

              @Override
              public void redraw() {
                // nothing to do
              }

              @Override
              public int getValue() {
                IBasicTrait background = (IBasicTrait) event.item.getData();
                return background.getCreationModel().getValue();
              }
            };
            final IntValuePaintListener intValuePaint = createIntValuePaint(redrawable);
            int x = getEntryWidth(table) - intValuePaint.getArea().getPreferredWidth();
            int itemHeight = table.getItemHeight();
            int imageHeight = intValuePaint.getArea().getPreferredHeight();
            int y = event.y + (itemHeight - imageHeight) / 2;
            Transform transform = new Transform(table.getDisplay());
            transform.translate(x, y);
            event.gc.setTransform(transform);
            intValuePaint.paintControl(event.gc);
          }
        });
        table.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseDown(MouseEvent e) {
            IntDisplayArea displayArea = createIntDisplayArea();
            int entryWidth = getEntryWidth(table);
            int textLength = entryWidth - displayArea.getPreferredWidth();
            int xWithinDisplayArea = e.x - textLength;
            if (xWithinDisplayArea >= 0) {
              int value = displayArea.getIndexForPosition(xWithinDisplayArea);
              TableItem item = table.getItem(new Point(e.x, e.y));
              IBasicTrait background = (IBasicTrait) item.getData();
              background.getCreationModel().setValue(value);
              table.redraw();
            }
          }
        });
        // TODO Abmelden des Listeners
        editorInput.getItem().addModificationListener(new IBackgroundModificationListener() {
          @Override
          public void traitAdded(IBasicTrait trait) {
            addBackgroundToTable(table, trait);
            entry.setText(DEFAULT_TEXT);
          }
        });
        for (IBasicTrait background : getPersistableEditorInput().getItem().getAllTraits()) {
          addBackgroundToTable(table, background);
        }
      }

      @Override
      public void setFocus() {
        entry.setFocus();
        entry.setText(DEFAULT_TEXT);
        entry.selectAll();
      }
    };
  }

  private int getEntryWidth(Table table) {
    return table.getClientArea().width;
  }

  private IntDisplayArea createIntDisplayArea() {
    IntViewImageProvider imageProvider = createImageProvider();
    Image passiveImage = imageProvider.createPassiveImage();
    return IntValuePaintListener.createDisplayArea(passiveImage, 5);
  }

  private IntValuePaintListener createIntValuePaint(IValueContainer redrawable) {
    IntViewImageProvider imageProvider = createImageProvider();
    Image passiveImage = imageProvider.createPassiveImage();
    Image valueImage = imageProvider.createActiveImage();
    return new IntValuePaintListener(redrawable, passiveImage, valueImage, 5);
  }

  private IntViewImageProvider createImageProvider() {
    final BackgroundEditorInput editorInput = (BackgroundEditorInput) getEditorInput();
    String activeImageId = editorInput.getCharacterType().getTraitImageId();
    return new IntViewImageProvider(activeImageId);
  }

  private void addBackgroundToTable(final Table table, IBasicTrait background) {
    TableItem item = new TableItem(table, SWT.NONE);
    item.setText(background.getTraitType().getId());
    item.setData(background);
  }
}