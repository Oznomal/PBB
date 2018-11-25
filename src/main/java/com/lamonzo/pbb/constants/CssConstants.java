package com.lamonzo.pbb.constants;

/**
 * This class contains constants that are used to reference
 * various CSS selectors and custom pseudo used throughout
 * the Java source code. This is necessary for working with
 * JavaFX styling in some cases.
 *
 * Unless otherwise noted these pseudo classes are located in
 * Common.css
 */
public final class CssConstants {

    //== FIELDS ==

    //CUSTOM CSS PSEUDO CLASSES
    public static final String PSEUDO_CENTERED = "centered";
    public static final String PSEUDO_PADDED = "padded";

    //TEXT FOR DYNAMIC BUTTONS
    public static final String UNSELECTED_BTN_TEXT = "Select Player";
    public static final String SELECTED_BTN_TEXT = "Remove Player";

    //== CONSTRUCTORS ==
    private CssConstants(){}
}
