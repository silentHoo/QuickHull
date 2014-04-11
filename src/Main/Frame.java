/**
    Copyright (c) 2014 Patrick Hillert <silent@gmx.biz>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; version 3 of the License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * A simple JFrame wrapper to show some information in a window.
 */
public class Frame extends JFrame {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 2190991212981210373L;

    /**
     * The text for the label.
     */
    private static JLabel textLabel;

    /**
     * Creates a new JFrame with the given title and sizes.
     *
     * @param title The name of the window caption.
     * @param width The width in pixels of the window.
     * @param height The height in pixels of the window.
     */
    Frame(final String title, final int width, final int height) {
        /*
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textLabel = new JLabel("Nothing here.", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(width, height));
        frame.getContentPane().add(textLabel, BorderLayout.CENTER);

        // display the window
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        */
    }

    /**
     * Updates the label's text.
     *
     * @param text The new text which replaces the current.
     */
    public final void updateLabel(final String text) {
        //textLabel.setText(text);
        System.out.println(text);
    }
}
