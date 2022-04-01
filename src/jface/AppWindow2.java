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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AppWindow2 extends ApplicationWindow {
    Shell mainWindow;

    public AppWindow2() {
	super(null);
    }

    public AppWindow2(Shell parentShell) {
	super(parentShell);
    }

    @Override
    protected void initializeBounds() {
	int xSize = 800;
	int ySize = 250;
	Rectangle sizes = getShell().getDisplay().getBounds();
	int x = sizes.width;
	int y = sizes.height;
	Rectangle mSize = new Rectangle((x - xSize) / 2, (y - ySize) / 2, xSize, ySize);
	getShell().setBounds(mSize);
	getShell().setMinimumSize(xSize, ySize);
	getShell().setMaximumSize(xSize + 400, ySize + 400);
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
	FillLayout fill2 = new FillLayout(SWT.VERTICAL);
	child2.setLayout(fill2);

	Composite child21 = new Composite(child2, SWT.BORDER);
	GridLayout grid21 = new GridLayout(4, false);
	grid21.marginHeight = 5;
	grid21.marginWidth = 5;
	grid21.horizontalSpacing = 10;
	grid21.verticalSpacing = 10;
	grid21.marginBottom = 10;
	child21.setLayout(grid21);

	GridData gridForLabel = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	gridForLabel.grabExcessHorizontalSpace = true;
	gridForLabel.horizontalSpan = 1;
	gridForLabel.widthHint = 40;
	gridForLabel.heightHint = 30;

	GridData gridForText = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_END);
	gridForText.grabExcessHorizontalSpace = true;
	gridForText.horizontalSpan = 3;
	gridForText.widthHint = 120;
	gridForText.heightHint = 30;

	GridData gridForButton = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
	gridForButton.grabExcessHorizontalSpace = true;
	gridForButton.horizontalSpan = 1;
	gridForButton.widthHint = 60;
	gridForButton.heightHint = 30;

	GridData gridForButtonNew = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
	gridForButtonNew.grabExcessHorizontalSpace = true;
	gridForButtonNew.horizontalSpan = 1;
	gridForButtonNew.horizontalIndent = 20;
	gridForButtonNew.widthHint = 60;
	gridForButtonNew.heightHint = 30;

	// Labels

	Label labelName = new Label(child21, SWT.NONE);
	labelName.setText("Name");
	labelName.setLayoutData(gridForLabel);

	Text textName = new Text(child21, SWT.BORDER);
	textName.setLayoutData(gridForText);

	Label labelGroup = new Label(child21, SWT.NONE);
	labelGroup.setText("Group");
	labelGroup.setLayoutData(gridForLabel);

	Text textGroup = new Text(child21, SWT.BORDER);
	textGroup.setLayoutData(gridForText);

	Label labelSWTDone = new Label(child21, SWT.NONE);
	labelSWTDone.setText("SWT task is done?");
	labelGroup.setLayoutData(gridForLabel);

	Button buttonSWTDone = new Button(child21, SWT.CHECK);
	buttonSWTDone.setSelection(false);
	buttonSWTDone.setLayoutData(gridForText);

	// Buttons

//	Composite child22 = new Composite(child2, SWT.BORDER);
//
//	GridLayout grid3 = new GridLayout(4, true);
//	grid3.horizontalSpacing = 5;
//	grid3.verticalSpacing = 0;
//	grid3.marginTop = 0;
//	grid3.marginBottom = 0;
//	grid3.marginLeft = 10;
//	grid3.marginRight = 10;

//	child22.setLayout(grid3);

	Button buttonNew = new Button(child21, SWT.PUSH);
	buttonNew.setLayoutData(gridForButtonNew);
	buttonNew.setText("New");

	Button buttonSave = new Button(child21, SWT.PUSH);
	buttonSave.setLayoutData(gridForButton);
	buttonSave.setText("Save");

	Button buttonDelete = new Button(child21, SWT.PUSH);
	buttonDelete.setLayoutData(gridForButton);
	buttonDelete.setText("Delete");

	Button buttonCancel = new Button(child21, SWT.PUSH);
	buttonCancel.setLayoutData(gridForButton);
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
