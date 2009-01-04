package zest.examples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;

/**
 * This snippet demonstrates a self loop with a label.
 * 
 * @author Ian Bull
 * 
 */
public class GraphSnippet9 {

	/**
	 * @param args
	 */
	@SuppressWarnings("nls")
  public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("GraphSnippet9");
		shell.setLayout(new FillLayout());
		shell.setSize(400, 400);

		final Graph graph = new Graph(shell, SWT.NONE);

		GraphNode a = new GraphNode(graph, ZestStyles.CONNECTIONS_DIRECTED, "Root");
		GraphConnection connection = new GraphConnection(graph, SWT.NONE, a, a);
		connection.setText("A to A");
		a.setLocation(100, 100);

		shell.open();
		while (!shell.isDisposed()) {
			while (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}

