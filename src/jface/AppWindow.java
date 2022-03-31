package jface;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AppWindow extends ApplicationWindow {
    Shell mainWindow;

    public AppWindow() {
	super(null);
    }

    public AppWindow(Shell parentShell) {
	super(parentShell);
    }

    @Override
    protected void initializeBounds() {
	int xSize = 800;
	int ySize = 400;
	Rectangle sizes = getShell().getDisplay().getBounds();
	int x = sizes.width;
	int y = sizes.height;
	Rectangle mSize = new Rectangle((x - xSize) / 2, (y - ySize) / 2, xSize, ySize);
	getShell().setBounds(mSize);
	getShell().setMinimumSize(xSize / 2, ySize / 2);
	getShell().setMaximumSize(xSize + 200, ySize + 200);
	getShell().setText("JFace application");
    }

    @Override
    protected Control createContents(Composite parent) {
	mainWindow = this.getShell();
	Group generalComposite = new Group(mainWindow, SWT.V_SCROLL);
	generalComposite.setLayout(new FillLayout());
	createSash(generalComposite);
	createMenu(generalComposite);

	parent.pack();
	return parent;
    }

    @Override
    protected MenuManager createMenuManager() {
	MenuManager menuManager = new MenuManager();
	return menuManager;
    }

    private void createSash(Composite c) {

	SashForm form = new SashForm(c, SWT.BORDER);
	form.setLayout(new FillLayout());
	form.SASH_WIDTH = 1;

	// Start create Left part
	Composite child1 = new Composite(form, SWT.BORDER);
	child1.setLayout(new FillLayout());

	TableViewer viewer = new TableViewer(child1, SWT.BORDER | SWT.FULL_SELECTION);

	// Start create right part
	Composite child2 = new Composite(form, SWT.BORDER);
	// GridLayout grid2 = new GridLayout(1, false);
	// grid2.marginHeight = 5;
	// grid2.marginWidth = 3;

	child2.setLayout(new FillLayout(SWT.VERTICAL));

	Composite child21 = new Composite(child2, SWT.BORDER);

	GridLayout gridLayout1 = new GridLayout();
	gridLayout1.numColumns = 4;
	gridLayout1.horizontalSpacing = 5;
	gridLayout1.makeColumnsEqualWidth = true;

	child21.setLayout(gridLayout1);

	GridData child21GridData = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
	child21.setLayoutData(child21GridData);

	GridData gridDataLabels = new GridData(100, 25);
	gridDataLabels.grabExcessHorizontalSpace = true;
	gridDataLabels.horizontalAlignment = SWT.LEFT;

	GridData gridDataText = new GridData(200, 25);
	gridDataText.grabExcessHorizontalSpace = true;
	gridDataText.horizontalAlignment = SWT.LEFT;
	gridDataText.horizontalSpan = 3;

	Label labelName = new Label(child21, SWT.BORDER);
	labelName.setText("Name");
	labelName.setLayoutData(gridDataLabels);

	Text textName = new Text(child21, SWT.BORDER);
	textName.setLayoutData(gridDataText);

	Label labelGroup = new Label(child21, SWT.BORDER);
	labelGroup.setText("Group");
	labelGroup.setLayoutData(gridDataLabels);

	Text textGroup = new Text(child21, SWT.BORDER);
	textGroup.setLayoutData(gridDataText);

	Label labelSWTDone = new Label(child21, SWT.BORDER);
	labelSWTDone.setText("SWT task is done?");
	labelGroup.setLayoutData(gridDataLabels);

	Button buttonSWTDone = new Button(child21, SWT.CHECK);
	buttonSWTDone.setSelection(false);
	buttonSWTDone.setLayoutData(gridDataText);

	GridLayout gridLayout2 = new GridLayout(4, true);
	gridLayout2.horizontalSpacing = 10;
	gridLayout2.verticalSpacing = 20;
	gridLayout2.marginTop = 150;
	gridLayout2.marginBottom = 20;
	gridLayout2.marginLeft = 10;
	gridLayout2.marginRight = 10;

	GridData buttonGridData = new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1);
	buttonGridData.widthHint = 70;
	buttonGridData.heightHint = 30;

	// buttonGridData.grabExcessHorizontalSpace = true;

	RowLayout rowLayout22 = new RowLayout();
	rowLayout22.pack = true;
	rowLayout22.wrap = false;
	rowLayout22.justify = false;
	rowLayout22.fill = true;
	rowLayout22.marginBottom = 10;
	rowLayout22.marginTop = 70;

	RowData rData = new RowData(70, 30);

	FillLayout fillLayout = new FillLayout();
	fillLayout.type = SWT.HORIZONTAL;
	fillLayout.marginWidth = 10;
	fillLayout.spacing = 5;
	fillLayout.marginHeight = 100;

	Composite child22 = new Composite(child2, SWT.BORDER);
	child22.setLayout(rowLayout22);

	Button buttonNew = new Button(child22, SWT.PUSH);
	buttonNew.setLayoutData(rData);
	buttonNew.setText("New");

	Button buttonSave = new Button(child22, SWT.PUSH);
	buttonSave.setLayoutData(rData);
	buttonSave.setText("Save");

	Button buttonDelete = new Button(child22, SWT.PUSH);
	buttonDelete.setLayoutData(rData);
	buttonDelete.setText("Delete");

	Button buttonCancel = new Button(child22, SWT.PUSH);
	buttonCancel.setLayoutData(rData);
	buttonCancel.setText("Cancel");

	form.setWeights(new int[] { 70, 50 });
    }

    private void createMenu(Composite c) {
	Shell shell = c.getShell();

	Menu bar = new Menu(shell, SWT.BAR);
	shell.setMenuBar(bar);

	// File menu

	MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
	fileItem.setText("&File");

	Menu submenu1 = new Menu(shell, SWT.DROP_DOWN);
	fileItem.setMenu(submenu1);

	MenuItem openItem = new MenuItem(submenu1, SWT.PUSH);
	openItem.addListener(SWT.Selection, e -> System.out.println("File opened"));
	openItem.setText("&Open file \tCtrl+O");
	openItem.setAccelerator(SWT.MOD1 + 'O');

	MenuItem saveItem = new MenuItem(submenu1, SWT.PUSH);
	saveItem.addListener(SWT.Selection, e -> System.out.println("File saved"));
	saveItem.setText("&Save file \tCtrl+S");
	saveItem.setAccelerator(SWT.MOD1 + 'S');

	MenuItem exitItem = new MenuItem(submenu1, SWT.PUSH);
	exitItem.addListener(SWT.Selection, e -> System.out.println("Exit program"));
	exitItem.setText("&Exit \tCtrl+X");
	exitItem.setAccelerator(SWT.MOD1 + 'X');

	// Edit menu
	MenuItem editMenuItem = new MenuItem(bar, SWT.CASCADE);
	editMenuItem.setText("&Edit");

	Menu submenu2 = new Menu(shell, SWT.DROP_DOWN);
	editMenuItem.setMenu(submenu2);

	MenuItem newItem = new MenuItem(submenu2, SWT.PUSH);
	newItem.addListener(SWT.Selection, e -> System.out.println("New row"));
	newItem.setText("&New \tCtrl+N");
	newItem.setAccelerator(SWT.MOD1 + 'N');

	MenuItem saveElementItem = new MenuItem(submenu2, SWT.PUSH);
	saveElementItem.addListener(SWT.Selection, e -> System.out.println("Save row"));
	saveElementItem.setText("&Save row \tCtrl+R");
	saveElementItem.setAccelerator(SWT.MOD1 + 'R');

	MenuItem deleteElementItem = new MenuItem(submenu2, SWT.PUSH);
	deleteElementItem.addListener(SWT.Selection, e -> System.out.println("Delete row"));
	deleteElementItem.setText("&Delete row \tCtrl+D");
	deleteElementItem.setAccelerator(SWT.MOD1 + 'D');

	MenuItem cancelElementItem = new MenuItem(submenu2, SWT.PUSH);
	cancelElementItem.addListener(SWT.Selection, e -> System.out.println("Cancel"));
	cancelElementItem.setText("&Cancel \tCtrl+Q");
	cancelElementItem.setAccelerator(SWT.MOD1 + 'Q');

	// About menu

	MenuItem aboutMenuItem = new MenuItem(bar, SWT.CASCADE);
	aboutMenuItem.setText("&About");

	Menu submenu3 = new Menu(shell, SWT.DROP_DOWN);
	aboutMenuItem.setMenu(submenu3);

	MenuItem aboutItem = new MenuItem(submenu3, SWT.PUSH);
	aboutItem.addListener(SWT.Selection, e -> System.out.println("About program"));
	aboutItem.setText("&About \tCtrl+A");
	aboutItem.setAccelerator(SWT.MOD1 + 'A');
    }
}
