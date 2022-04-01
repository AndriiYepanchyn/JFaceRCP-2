package jface;

import org.eclipse.swt.widgets.Display;

public class Main {
    public static void main(String[] args) {
	AppWindow2 win = new AppWindow2();
	win.setBlockOnOpen(true);
	win.open();
	Display.getCurrent().dispose();
    }
}
