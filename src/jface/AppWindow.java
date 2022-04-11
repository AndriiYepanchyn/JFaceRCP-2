package jface;

import java.lang.reflect.Method;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
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

import actions.ExitAction;
import actions.NewFileAction;
import actions.SaveAction;

public class AppWindow extends ApplicationWindow {
    Shell mainWindow;
    Font font;
    Text textName;
    Text textGroup;
    Button buttonSWTDone;
    int indexToRemove;
    Session ses = SessionManager.getInstatnce();
    Entity newEntity;

    public AppWindow() {
	super(null);
	addMenuBar();
	ses.addEntity("name 1", "1", true);
	ses.addEntity("name 2", "2", false);
	ses.addEntity("name 3", "1", true);
	ses.addEntity("name 4", "2", false);
	ses.addEntity("name 5", "1", false);
	ses.addEntity("name 6", "2", true);
    }

    public AppWindow(Shell parentShell) {
	super(parentShell);
    }

    @Override
    protected void initializeBounds() {
	int xSize = 800;
	int ySize = 270;
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

	menuFile.add(new NewFileAction(this));
	menuFile.add(new SaveAction(this));
	menuFile.add(new Action("Open \tCtrl+O") {
	});
	menuFile.add(new ExitAction(this));

	// Edit Menu
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
	TableViewer viewer = createTableViewer(child1);

	// Start create right part
	Composite child2 = new Composite(form, SWT.BORDER);
	FillLayout fill2 = new FillLayout(SWT.VERTICAL);
	child2.setLayout(fill2);

	Composite child21 = createRightFieldArea(child2);

	// Labels
	Label labelName = new Label(child21, SWT.NONE);
	labelName.setText("Name");
	labelName.setLayoutData(createGridForLabel());

	textName = new Text(child21, SWT.BORDER);
	textName.setLayoutData(createGridForText());
	textName.addModifyListener(new ModifyListener() {
	    @Override
	    public void modifyText(ModifyEvent e) {
	    }
	});

	Label labelGroup = new Label(child21, SWT.NONE);
	labelGroup.setText("Group");
	labelGroup.setLayoutData(createGridForLabel());

	textGroup = new Text(child21, SWT.BORDER);
	textGroup.setLayoutData(createGridForText());

	Label labelSWTDone = new Label(child21, SWT.NONE);
	labelSWTDone.setText("SWT task is done?");
	labelSWTDone.setLayoutData(createGridForLabel());

	buttonSWTDone = new Button(child21, SWT.CHECK);
	buttonSWTDone.setSelection(false);
	buttonSWTDone.setLayoutData(createGridForText());

	// Buttons
	Button buttonNew = createButton(child21, "New", createGridForButtonNew());
	buttonNew.addSelectionListener(new SelectionListener() {

	    @Override
	    public void widgetSelected(SelectionEvent e) {
		if (newEntity == null) {
		    clearFields();
		    newEntity = new Entity();
		}
	    }

	    @Override
	    public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	    }
	});

	Button buttonSave = createButton(child21, "Save", createGridForButton());
	buttonSave.addSelectionListener(new SelectionListener() {
//TODO
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		ses.name = textName.getText();
		ses.group = textGroup.getText();
		ses.swtDone = (buttonSWTDone.getSelection());
		if (newEntity == null) {
		    IStructuredSelection selection = viewer.getStructuredSelection();
		    newEntity = ((Entity) selection.getFirstElement());

		    ses.activeRecord.setName(ses.name);
		    ses.activeRecord.setGroup(ses.group);
		    ses.activeRecord.setSwtDone(ses.swtDone);
		    ses.addEntity(ses.activeRecord);
		} else {
		    ses.addEntity(ses.name, ses.group, ses.swtDone);

		}
		viewer.refresh();
		newEntity = null;
	    }

	    @Override
	    public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	    }
	});

	Button buttonDelete = createButton(child21, "Delete", createGridForButton());
	buttonDelete.addSelectionListener(new SelectionListener() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		ses.removeCurrentObject();
		viewer.getTable().deselectAll();
		setFields();
		viewer.refresh();
	    }

	    @Override
	    public void widgetDefaultSelected(SelectionEvent e) {
	    }
	});

	Button buttonCancel = createButton(child21, "Cancel", createGridForButton());
	buttonCancel.addSelectionListener(new SelectionListener() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		viewer.getTable().deselectAll();
		clearFields();
		viewer.refresh();
	    }

	    @Override
	    public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	    }
	});

	form.setWeights(new int[] { 70, 50 });
    }

    public static Composite createRightFieldArea(Composite parent) {
	Composite child = new Composite(parent, SWT.BORDER);
	GridLayout gridLayout = new GridLayout(4, false);
	gridLayout.marginHeight = 5;
	gridLayout.marginWidth = 5;
	gridLayout.horizontalSpacing = 10;
	gridLayout.verticalSpacing = 10;
	gridLayout.marginBottom = 10;
	child.setLayout(gridLayout);
	return child;
    }

    public static GridData createGridForLabel() {
	GridData gridForLabel = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	gridForLabel.grabExcessHorizontalSpace = true;
	gridForLabel.horizontalSpan = 1;
	gridForLabel.widthHint = 60;
	gridForLabel.heightHint = 30;
	return gridForLabel;
    }

    public static GridData createGridForText() {
	GridData gridForText = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_END);
	gridForText.grabExcessHorizontalSpace = true;
	gridForText.horizontalSpan = 3;
	gridForText.widthHint = 120;
	gridForText.heightHint = 30;
	return gridForText;
    }

    public static GridData createGridForButton() {
	GridData gridForButton = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
	gridForButton.grabExcessHorizontalSpace = true;
	gridForButton.horizontalSpan = 1;
	gridForButton.widthHint = 60;
	gridForButton.heightHint = 30;
	return gridForButton;
    }

    public static GridData createGridForButtonNew() {
	GridData gridForButtonNew = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
	gridForButtonNew.grabExcessHorizontalSpace = true;
	gridForButtonNew.horizontalSpan = 1;
	gridForButtonNew.horizontalIndent = 20;
	gridForButtonNew.widthHint = 60;
	gridForButtonNew.heightHint = 30;
	return gridForButtonNew;
    }

    public Button createButton(Composite parent, String text, GridData buttonGridData) {
	Button button = new Button(parent, SWT.PUSH);
	button.setLayoutData(buttonGridData);
	button.setText(text);

	return button;
    }

    public TableViewer createTableViewer(Composite parent) {
	TableViewer viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
	viewer.setContentProvider(ArrayContentProvider.getInstance());

	String[] columnNameStrings = Entity.getArrayOfEntityFields();
	for (String colName : columnNameStrings) {
	    createColumnName(viewer, colName);
	}

	viewer.getTable().getColumn(2).setWidth(80);
	viewer.setInput(ses.getAllRecords());
	viewer.getTable().setLinesVisible(true);
	viewer.getTable().setHeaderVisible(true);
	viewer.getTable().setHeaderBackground(new Color(181, 181, 181));

	viewer.addSelectionChangedListener(new ISelectionChangedListener() {
	    @Override
	    public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection selection = viewer.getStructuredSelection();
		ses.activeRecord = (Entity) selection.getFirstElement();
		ses.name = ses.activeRecord.getName();
		ses.group = ses.activeRecord.getGroup();
		ses.swtDone = ses.activeRecord.getSwtDone();
		setFields();
	    }
	});
	return viewer;
    }

    private void createColumnName(TableViewer viewer, String columnName) {
	TableViewerColumn column = new TableViewerColumn(viewer, SWT.CHECK);
	column.getColumn().setText(columnName.toUpperCase());
	column.getColumn().setWidth(180);
	column.getColumn().setAlignment(SWT.CENTER);
	column.getColumn().setMoveable(false);
	column.getColumn().setResizable(false);
	column.setLabelProvider(new ColumnLabelProvider() {
	    @Override
	    public String getText(Object element) {
		return getElementValue(columnName, element);
	    }
	});
	ColumnViewerComparator cSorter = new ColumnViewerComparator(viewer, column) {
	    @Override
	    protected int doCompare(Viewer viewer, Object e1, Object e2) {
		String p1 = getElementValue(columnName, e1);
		String p2 = getElementValue(columnName, e2);
		return p1.compareToIgnoreCase(p2);
	    }
	};
    }

    private String getElementValue(String columnName, Object element) {
	String answerString = "";
	try {
	    Method tmpMethod = Entity.getRefferedMethods().get(columnName);
	    answerString = String.valueOf(tmpMethod.invoke((Entity) element));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return answerString;
    }

    private void setFields() {

	textName.setText(ses.name);
	textName.redraw();
	textGroup.setText(ses.group);
	textGroup.redraw();
	buttonSWTDone.setSelection(ses.swtDone);
	buttonSWTDone.redraw();
    }

    private void clearFields() {
	ses.name = "";
	ses.group = "";
	ses.swtDone = false;
	setFields();
    }
}
