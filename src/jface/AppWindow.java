package jface;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AppWindow extends ApplicationWindow {
    Shell mainWindow;
    Font font;

    public AppWindow() {
	super(null);
	addMenuBar();
    }

    public AppWindow(Shell parentShell) {
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

	parent.pack();
	return parent;
    }

    @Override
    protected MenuManager createMenuManager() {
	MenuManager mainMenu = new MenuManager();
	MenuManager menuFile = new MenuManager("&File", "1");
	mainMenu.add(menuFile);

	menuFile.add(new Action("New file \tCtrl+N") {
	    	System.out.println("ggg");
	});

	menuFile.add(new Action("Open \tCtrl+O") {

	});

	menuFile.add(new Action("Save \tCtrl+S") {
	});

	menuFile.add(new Action("Exit \tCtrl+X") {
	});

	MenuManager menuEdit = new MenuManager("&Edit", "2");
	mainMenu.add(menuEdit);

	menuEdit.add(new Action("New row \tCtrl+R") {
	});

	menuEdit.add(new Action("Save record \tCtrl+M") {
	});

	menuEdit.add(new Action("Delete record \tCtrl+D") {
	});

	menuEdit.add(new Action("Cancel \tCtrl+Q") {
	});

	MenuManager menuAbout = new MenuManager("&About", "3");
	mainMenu.add(menuAbout);
	menuAbout.add(new Action("About program \tCtrl+F1") {
	});

	return mainMenu;
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
	gridForLabel.widthHint = 60;
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
	textName.addModifyListener(new ModifyListener() {

	    @Override
	    public void modifyText(ModifyEvent e) {
		// TODO
		System.out.println("key pressed");
	    }
	});
	;

	Label labelGroup = new Label(child21, SWT.NONE);
	labelGroup.setText("Group");
	labelGroup.setLayoutData(gridForLabel);

	Text textGroup = new Text(child21, SWT.BORDER);
	textGroup.setLayoutData(gridForText);

	Label labelSWTDone = new Label(child21, SWT.NONE);
	labelSWTDone.setText("SWT task is done?");
	labelSWTDone.setLayoutData(gridForLabel);

	Button buttonSWTDone = new Button(child21, SWT.CHECK);
	buttonSWTDone.setSelection(false);
	buttonSWTDone.setLayoutData(gridForText);

	// Buttons

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

//    private static Text createTextName(Composite c) {
//	Text textName = new Text(c, SWT.BORDER);
//	textName.setLayoutData(gridForText);
//	textName.addModifyListener(new ModifyListener() {
//
//	    @Override
//	    public void modifyText(ModifyEvent e) {
//		// TODO
//		System.out.println("key pressed");
//	    }
//	});
//	;
//    }

}
