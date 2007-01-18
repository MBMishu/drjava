/*BEGIN_COPYRIGHT_BLOCK
 *
 * This file is part of DrJava.  Download the current version of this project from http://www.drjava.org/
 * or http://sourceforge.net/projects/drjava/
 *
 * DrJava Open Source License
 * 
 * Copyright (C) 2001-2005 JavaPLT group at Rice University (javaplt@rice.edu).  All rights reserved.
 *
 * Developed by:   Java Programming Languages Team, Rice University, http://www.cs.rice.edu/~javaplt/
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal with the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and 
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 *     - Redistributions of source code must retain the above copyright notice, this list of conditions and the 
 *       following disclaimers.
 *     - Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the 
 *       following disclaimers in the documentation and/or other materials provided with the distribution.
 *     - Neither the names of DrJava, the JavaPLT, Rice University, nor the names of its contributors may be used to 
 *       endorse or promote products derived from this Software without specific prior written permission.
 *     - Products derived from this software may not be called "DrJava" nor use the term "DrJava" as part of their 
 *       names without prior written permission from the JavaPLT group.  For permission, write to javaplt@rice.edu.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO 
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * WITH THE SOFTWARE.
 * 
 END_COPYRIGHT_BLOCK*/

package edu.rice.cs.drjava.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A splash screen window to be displayed as DrJava is first starting up.
 * @version $Id$
 */
public class SplashScreen extends JWindow {
  private static final String SPLASH_ICON = "splash.png";
  private static final int PAUSE_TIME = 4000; // in milliseconds
  
  private ImageIcon _icon;

  /** Creates a new splash screen, but does not display it.  Display the splash screen using show() and close it 
    * with dispose().
    */
  public SplashScreen() {
    _icon = MainFrame.getIcon(SPLASH_ICON);
    getContentPane().add(new JLabel(_icon, SwingConstants.CENTER));
    setSize(_icon.getIconWidth(), _icon.getIconHeight());
    //for multi-monitor support
    //Question: do we want it to popup on the first monitor always?
    GraphicsDevice[] dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
    Rectangle rec = dev[0].getDefaultConfiguration().getBounds();
    Point ownerLoc = rec.getLocation();
    Dimension ownerSize = rec.getSize();
    Dimension frameSize = getSize();
    setLocation(ownerLoc.x + (ownerSize.width - frameSize.width) / 2,
                ownerLoc.y + (ownerSize.height - frameSize.height) / 2);
  }
  
  /** Display the splash screen, and schedule it to be removed after a delay.  This does not
    * need to run on the event thread.
    */
  public void flash() {
    setVisible(true);
    repaint();
    Timer cleanup = new Timer(PAUSE_TIME, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    cleanup.setRepeats(false);
    cleanup.start();
  }
  
}
