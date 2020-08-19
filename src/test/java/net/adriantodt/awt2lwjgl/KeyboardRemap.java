package net.adriantodt.awt2lwjgl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class KeyboardRemap {
    public static final int LWJGL_KEY_ESCAPE = 0x01;
    public static final int LWJGL_KEY_1 = 0x02;
    public static final int LWJGL_KEY_2 = 0x03;
    public static final int LWJGL_KEY_3 = 0x04;
    public static final int LWJGL_KEY_4 = 0x05;
    public static final int LWJGL_KEY_5 = 0x06;
    public static final int LWJGL_KEY_6 = 0x07;
    public static final int LWJGL_KEY_7 = 0x08;
    public static final int LWJGL_KEY_8 = 0x09;
    public static final int LWJGL_KEY_9 = 0x0A;
    public static final int LWJGL_KEY_0 = 0x0B;
    public static final int LWJGL_KEY_MINUS = 0x0C; /* - on main keyboard */
    public static final int LWJGL_KEY_EQUALS = 0x0D;
    public static final int LWJGL_KEY_BACK = 0x0E; /* backspace */
    public static final int LWJGL_KEY_TAB = 0x0F;
    public static final int LWJGL_KEY_Q = 0x10;
    public static final int LWJGL_KEY_W = 0x11;
    public static final int LWJGL_KEY_E = 0x12;
    public static final int LWJGL_KEY_R = 0x13;
    public static final int LWJGL_KEY_T = 0x14;
    public static final int LWJGL_KEY_Y = 0x15;
    public static final int LWJGL_KEY_U = 0x16;
    public static final int LWJGL_KEY_I = 0x17;
    public static final int LWJGL_KEY_O = 0x18;
    public static final int LWJGL_KEY_P = 0x19;
    public static final int LWJGL_KEY_LBRACKET = 0x1A;
    public static final int LWJGL_KEY_RBRACKET = 0x1B;
    public static final int LWJGL_KEY_RETURN = 0x1C; /* Enter on main keyboard */
    public static final int LWJGL_KEY_LCONTROL = 0x1D;
    public static final int LWJGL_KEY_A = 0x1E;
    public static final int LWJGL_KEY_S = 0x1F;
    public static final int LWJGL_KEY_D = 0x20;
    public static final int LWJGL_KEY_F = 0x21;
    public static final int LWJGL_KEY_G = 0x22;
    public static final int LWJGL_KEY_H = 0x23;
    public static final int LWJGL_KEY_J = 0x24;
    public static final int LWJGL_KEY_K = 0x25;
    public static final int LWJGL_KEY_L = 0x26;
    public static final int LWJGL_KEY_SEMICOLON = 0x27;
    public static final int LWJGL_KEY_APOSTROPHE = 0x28;
    public static final int LWJGL_KEY_GRAVE = 0x29; /* accent grave */
    public static final int LWJGL_KEY_LSHIFT = 0x2A;
    public static final int LWJGL_KEY_BACKSLASH = 0x2B;
    public static final int LWJGL_KEY_Z = 0x2C;
    public static final int LWJGL_KEY_X = 0x2D;
    public static final int LWJGL_KEY_C = 0x2E;
    public static final int LWJGL_KEY_V = 0x2F;
    public static final int LWJGL_KEY_B = 0x30;
    public static final int LWJGL_KEY_N = 0x31;
    public static final int LWJGL_KEY_M = 0x32;
    public static final int LWJGL_KEY_COMMA = 0x33;
    public static final int LWJGL_KEY_PERIOD = 0x34; /* . on main keyboard */
    public static final int LWJGL_KEY_SLASH = 0x35; /* / on main keyboard */
    public static final int LWJGL_KEY_RSHIFT = 0x36;
    public static final int LWJGL_KEY_MULTIPLY = 0x37; /* * on numeric keypad */
    public static final int LWJGL_KEY_LMENU = 0x38; /* left Alt */
    public static final int LWJGL_KEY_SPACE = 0x39;
    public static final int LWJGL_KEY_CAPITAL = 0x3A;
    public static final int LWJGL_KEY_F1 = 0x3B;
    public static final int LWJGL_KEY_F2 = 0x3C;
    public static final int LWJGL_KEY_F3 = 0x3D;
    public static final int LWJGL_KEY_F4 = 0x3E;
    public static final int LWJGL_KEY_F5 = 0x3F;
    public static final int LWJGL_KEY_F6 = 0x40;
    public static final int LWJGL_KEY_F7 = 0x41;
    public static final int LWJGL_KEY_F8 = 0x42;
    public static final int LWJGL_KEY_F9 = 0x43;
    public static final int LWJGL_KEY_F10 = 0x44;
    public static final int LWJGL_KEY_NUMLOCK = 0x45;
    public static final int LWJGL_KEY_SCROLL = 0x46; /* Scroll Lock */
    public static final int LWJGL_KEY_NUMPAD7 = 0x47;
    public static final int LWJGL_KEY_NUMPAD8 = 0x48;
    public static final int LWJGL_KEY_NUMPAD9 = 0x49;
    public static final int LWJGL_KEY_SUBTRACT = 0x4A; /* - on numeric keypad */
    public static final int LWJGL_KEY_NUMPAD4 = 0x4B;
    public static final int LWJGL_KEY_NUMPAD5 = 0x4C;
    public static final int LWJGL_KEY_NUMPAD6 = 0x4D;
    public static final int LWJGL_KEY_ADD = 0x4E; /* + on numeric keypad */
    public static final int LWJGL_KEY_NUMPAD1 = 0x4F;
    public static final int LWJGL_KEY_NUMPAD2 = 0x50;
    public static final int LWJGL_KEY_NUMPAD3 = 0x51;
    public static final int LWJGL_KEY_NUMPAD0 = 0x52;
    public static final int LWJGL_KEY_DECIMAL = 0x53; /* . on numeric keypad */
    public static final int LWJGL_KEY_F11 = 0x57;
    public static final int LWJGL_KEY_F12 = 0x58;
    public static final int LWJGL_KEY_F13 = 0x64; /*                     (NEC PC98) */
    public static final int LWJGL_KEY_F14 = 0x65; /*                     (NEC PC98) */
    public static final int LWJGL_KEY_F15 = 0x66; /*                     (NEC PC98) */
    public static final int LWJGL_KEY_F16 = 0x67; /* Extended Function keys - (Mac) */
    public static final int LWJGL_KEY_F17 = 0x68;
    public static final int LWJGL_KEY_F18 = 0x69;
    public static final int LWJGL_KEY_KANA = 0x70; /* (Japanese keyboard)            */
    public static final int LWJGL_KEY_F19 = 0x71; /* Extended Function keys - (Mac) */
    public static final int LWJGL_KEY_CONVERT = 0x79; /* (Japanese keyboard)            */
    public static final int LWJGL_KEY_NOCONVERT = 0x7B; /* (Japanese keyboard)            */
    public static final int LWJGL_KEY_YEN = 0x7D; /* (Japanese keyboard)            */
    public static final int LWJGL_KEY_NUMPADEQUALS = 0x8D; /* = on numeric keypad (NEC PC98) */
    public static final int LWJGL_KEY_CIRCUMFLEX = 0x90; /* (Japanese keyboard)            */
    public static final int LWJGL_KEY_AT = 0x91; /*                     (NEC PC98) */
    public static final int LWJGL_KEY_COLON = 0x92; /*                     (NEC PC98) */
    public static final int LWJGL_KEY_UNDERLINE = 0x93; /*                     (NEC PC98) */
    public static final int LWJGL_KEY_KANJI = 0x94; /* (Japanese keyboard)            */
    public static final int LWJGL_KEY_STOP = 0x95; /*                     (NEC PC98) */
    public static final int LWJGL_KEY_AX = 0x96; /*                     (Japan AX) */
    public static final int LWJGL_KEY_UNLABELED = 0x97; /*                        (J3100) */
    public static final int LWJGL_KEY_NUMPADENTER = 0x9C; /* Enter on numeric keypad */
    public static final int LWJGL_KEY_RCONTROL = 0x9D;
    public static final int LWJGL_KEY_SECTION = 0xA7; /* Section symbol (Mac) */
    public static final int LWJGL_KEY_NUMPADCOMMA = 0xB3; /* , on numeric keypad (NEC PC98) */
    public static final int LWJGL_KEY_DIVIDE = 0xB5; /* / on numeric keypad */
    public static final int LWJGL_KEY_SYSRQ = 0xB7;
    public static final int LWJGL_KEY_RMENU = 0xB8; /* right Alt */
    public static final int LWJGL_KEY_FUNCTION = 0xC4; /* Function (Mac) */
    public static final int LWJGL_KEY_PAUSE = 0xC5; /* Pause */
    public static final int LWJGL_KEY_HOME = 0xC7; /* Home on arrow keypad */
    public static final int LWJGL_KEY_UP = 0xC8; /* UpArrow on arrow keypad */
    public static final int LWJGL_KEY_PRIOR = 0xC9; /* PgUp on arrow keypad */
    public static final int LWJGL_KEY_LEFT = 0xCB; /* LeftArrow on arrow keypad */
    public static final int LWJGL_KEY_RIGHT = 0xCD; /* RightArrow on arrow keypad */
    public static final int LWJGL_KEY_END = 0xCF; /* End on arrow keypad */
    public static final int LWJGL_KEY_DOWN = 0xD0; /* DownArrow on arrow keypad */
    public static final int LWJGL_KEY_NEXT = 0xD1; /* PgDn on arrow keypad */
    public static final int LWJGL_KEY_INSERT = 0xD2; /* Insert on arrow keypad */
    public static final int LWJGL_KEY_DELETE = 0xD3; /* Delete on arrow keypad */
    public static final int LWJGL_KEY_CLEAR = 0xDA; /* Clear key (Mac) */
    public static final int LWJGL_KEY_LMETA = 0xDB; /* Left Windows/Option key */
    public static final int LWJGL_KEY_RMETA = 0xDC; /* Right Windows/Option key */
    public static final int LWJGL_KEY_APPS = 0xDD; /* AppMenu key */
    public static final int LWJGL_KEY_POWER = 0xDE;
    public static final int LWJGL_KEY_SLEEP = 0xDF;

    /**
     * Constant for the ENTER virtual key.
     */
    public static final int AWT_KEY_ENTER = '\n';

    /**
     * Constant for the BACK_SPACE virtual key.
     */
    public static final int AWT_KEY_BACK_SPACE = '\b';

    /**
     * Constant for the TAB virtual key.
     */
    public static final int AWT_KEY_TAB = '\t';

    /**
     * Constant for the CANCEL virtual key.
     */
    public static final int AWT_KEY_CANCEL = 0x03;

    /**
     * Constant for the CLEAR virtual key.
     */
    public static final int AWT_KEY_CLEAR = 0x0C;

    /**
     * Constant for the SHIFT virtual key.
     */
    public static final int AWT_KEY_SHIFT = 0x10;

    /**
     * Constant for the CONTROL virtual key.
     */
    public static final int AWT_KEY_CONTROL = 0x11;

    /**
     * Constant for the ALT virtual key.
     */
    public static final int AWT_KEY_ALT = 0x12;

    /**
     * Constant for the PAUSE virtual key.
     */
    public static final int AWT_KEY_PAUSE = 0x13;

    /**
     * Constant for the CAPS_LOCK virtual key.
     */
    public static final int AWT_KEY_CAPS_LOCK = 0x14;

    /**
     * Constant for the ESCAPE virtual key.
     */
    public static final int AWT_KEY_ESCAPE = 0x1B;

    /**
     * Constant for the SPACE virtual key.
     */
    public static final int AWT_KEY_SPACE = 0x20;

    /**
     * Constant for the PAGE_UP virtual key.
     */
    public static final int AWT_KEY_PAGE_UP = 0x21;

    /**
     * Constant for the PAGE_DOWN virtual key.
     */
    public static final int AWT_KEY_PAGE_DOWN = 0x22;

    /**
     * Constant for the END virtual key.
     */
    public static final int AWT_KEY_END = 0x23;

    /**
     * Constant for the HOME virtual key.
     */
    public static final int AWT_KEY_HOME = 0x24;

    /**
     * Constant for the non-numpad <b>left</b> arrow key.
     *
     * @see #AWT_KEY_KP_LEFT
     */
    public static final int AWT_KEY_LEFT = 0x25;

    /**
     * Constant for the non-numpad <b>up</b> arrow key.
     *
     * @see #AWT_KEY_KP_UP
     */
    public static final int AWT_KEY_UP = 0x26;

    /**
     * Constant for the non-numpad <b>right</b> arrow key.
     *
     * @see #AWT_KEY_KP_RIGHT
     */
    public static final int AWT_KEY_RIGHT = 0x27;

    /**
     * Constant for the non-numpad <b>down</b> arrow key.
     *
     * @see #AWT_KEY_KP_DOWN
     */
    public static final int AWT_KEY_DOWN = 0x28;

    /**
     * Constant for the comma key, ","
     */
    public static final int AWT_KEY_COMMA = 0x2C;

    /**
     * Constant for the minus key, "-"
     *
     * @since 1.2
     */
    public static final int AWT_KEY_MINUS = 0x2D;

    /**
     * Constant for the period key, "."
     */
    public static final int AWT_KEY_PERIOD = 0x2E;

    /**
     * Constant for the forward slash key, "/"
     */
    public static final int AWT_KEY_SLASH = 0x2F;

    /**
     * Constant for the "0" key.
     */
    public static final int AWT_KEY_0 = 0x30;

    /**
     * Constant for the "1" key.
     */
    public static final int AWT_KEY_1 = 0x31;

    /**
     * Constant for the "2" key.
     */
    public static final int AWT_KEY_2 = 0x32;

    /**
     * Constant for the "3" key.
     */
    public static final int AWT_KEY_3 = 0x33;

    /**
     * Constant for the "4" key.
     */
    public static final int AWT_KEY_4 = 0x34;

    /**
     * Constant for the "5" key.
     */
    public static final int AWT_KEY_5 = 0x35;

    /**
     * Constant for the "6" key.
     */
    public static final int AWT_KEY_6 = 0x36;

    /**
     * Constant for the "7" key.
     */
    public static final int AWT_KEY_7 = 0x37;

    /**
     * Constant for the "8" key.
     */
    public static final int AWT_KEY_8 = 0x38;

    /**
     * Constant for the "9" key.
     */
    public static final int AWT_KEY_9 = 0x39;

    /**
     * Constant for the semicolon key, ";"
     */
    public static final int AWT_KEY_SEMICOLON = 0x3B;

    /**
     * Constant for the equals key, "="
     */
    public static final int AWT_KEY_EQUALS = 0x3D;

    /**
     * Constant for the "A" key.
     */
    public static final int AWT_KEY_A = 0x41;

    /**
     * Constant for the "B" key.
     */
    public static final int AWT_KEY_B = 0x42;

    /**
     * Constant for the "C" key.
     */
    public static final int AWT_KEY_C = 0x43;

    /**
     * Constant for the "D" key.
     */
    public static final int AWT_KEY_D = 0x44;

    /**
     * Constant for the "E" key.
     */
    public static final int AWT_KEY_E = 0x45;

    /**
     * Constant for the "F" key.
     */
    public static final int AWT_KEY_F = 0x46;

    /**
     * Constant for the "G" key.
     */
    public static final int AWT_KEY_G = 0x47;

    /**
     * Constant for the "H" key.
     */
    public static final int AWT_KEY_H = 0x48;

    /**
     * Constant for the "I" key.
     */
    public static final int AWT_KEY_I = 0x49;

    /**
     * Constant for the "J" key.
     */
    public static final int AWT_KEY_J = 0x4A;

    /**
     * Constant for the "K" key.
     */
    public static final int AWT_KEY_K = 0x4B;

    /**
     * Constant for the "L" key.
     */
    public static final int AWT_KEY_L = 0x4C;

    /**
     * Constant for the "M" key.
     */
    public static final int AWT_KEY_M = 0x4D;

    /**
     * Constant for the "N" key.
     */
    public static final int AWT_KEY_N = 0x4E;

    /**
     * Constant for the "O" key.
     */
    public static final int AWT_KEY_O = 0x4F;

    /**
     * Constant for the "P" key.
     */
    public static final int AWT_KEY_P = 0x50;

    /**
     * Constant for the "Q" key.
     */
    public static final int AWT_KEY_Q = 0x51;

    /**
     * Constant for the "R" key.
     */
    public static final int AWT_KEY_R = 0x52;

    /**
     * Constant for the "S" key.
     */
    public static final int AWT_KEY_S = 0x53;

    /**
     * Constant for the "T" key.
     */
    public static final int AWT_KEY_T = 0x54;

    /**
     * Constant for the "U" key.
     */
    public static final int AWT_KEY_U = 0x55;

    /**
     * Constant for the "V" key.
     */
    public static final int AWT_KEY_V = 0x56;

    /**
     * Constant for the "W" key.
     */
    public static final int AWT_KEY_W = 0x57;

    /**
     * Constant for the "X" key.
     */
    public static final int AWT_KEY_X = 0x58;

    /**
     * Constant for the "Y" key.
     */
    public static final int AWT_KEY_Y = 0x59;

    /**
     * Constant for the "Z" key.
     */
    public static final int AWT_KEY_Z = 0x5A;

    /**
     * Constant for the open bracket key, "["
     */
    public static final int AWT_KEY_OPEN_BRACKET = 0x5B;

    /**
     * Constant for the back slash key, "\"
     */
    public static final int AWT_KEY_BACK_SLASH = 0x5C;

    /**
     * Constant for the close bracket key, "]"
     */
    public static final int AWT_KEY_CLOSE_BRACKET = 0x5D;

    /**
     * Constant for the number pad "0" key.
     */
    public static final int AWT_KEY_NUMPAD0 = 0x60;

    /**
     * Constant for the number pad "1" key.
     */
    public static final int AWT_KEY_NUMPAD1 = 0x61;

    /**
     * Constant for the number pad "2" key.
     */
    public static final int AWT_KEY_NUMPAD2 = 0x62;

    /**
     * Constant for the number pad "3" key.
     */
    public static final int AWT_KEY_NUMPAD3 = 0x63;

    /**
     * Constant for the number pad "4" key.
     */
    public static final int AWT_KEY_NUMPAD4 = 0x64;

    /**
     * Constant for the number pad "5" key.
     */
    public static final int AWT_KEY_NUMPAD5 = 0x65;

    /**
     * Constant for the number pad "6" key.
     */
    public static final int AWT_KEY_NUMPAD6 = 0x66;

    /**
     * Constant for the number pad "7" key.
     */
    public static final int AWT_KEY_NUMPAD7 = 0x67;

    /**
     * Constant for the number pad "8" key.
     */
    public static final int AWT_KEY_NUMPAD8 = 0x68;

    /**
     * Constant for the number pad "9" key.
     */
    public static final int AWT_KEY_NUMPAD9 = 0x69;

    /**
     * Constant for the number pad multiply key.
     */
    public static final int AWT_KEY_MULTIPLY = 0x6A;

    /**
     * Constant for the number pad add key.
     */
    public static final int AWT_KEY_ADD = 0x6B;

    /**
     * Constant for the Numpad Separator key.
     *
     * @since 1.4
     */
    public static final int AWT_KEY_SEPARATOR = 0x6C;

    /**
     * Constant for the number pad subtract key.
     */
    public static final int AWT_KEY_SUBTRACT = 0x6D;

    /**
     * Constant for the number pad decimal point key.
     */
    public static final int AWT_KEY_DECIMAL = 0x6E;

    /**
     * Constant for the number pad divide key.
     */
    public static final int AWT_KEY_DIVIDE = 0x6F;

    /**
     * Constant for the delete key.
     */
    public static final int AWT_KEY_DELETE = 0x7F; /* ASCII DEL */

    /**
     * Constant for the NUM_LOCK key.
     */
    public static final int AWT_KEY_NUM_LOCK = 0x90;

    /**
     * Constant for the SCROLL_LOCK key.
     */
    public static final int AWT_KEY_SCROLL_LOCK = 0x91;

    /**
     * Constant for the F1 function key.
     */
    public static final int AWT_KEY_F1 = 0x70;

    /**
     * Constant for the F2 function key.
     */
    public static final int AWT_KEY_F2 = 0x71;

    /**
     * Constant for the F3 function key.
     */
    public static final int AWT_KEY_F3 = 0x72;

    /**
     * Constant for the F4 function key.
     */
    public static final int AWT_KEY_F4 = 0x73;

    /**
     * Constant for the F5 function key.
     */
    public static final int AWT_KEY_F5 = 0x74;

    /**
     * Constant for the F6 function key.
     */
    public static final int AWT_KEY_F6 = 0x75;

    /**
     * Constant for the F7 function key.
     */
    public static final int AWT_KEY_F7 = 0x76;

    /**
     * Constant for the F8 function key.
     */
    public static final int AWT_KEY_F8 = 0x77;

    /**
     * Constant for the F9 function key.
     */
    public static final int AWT_KEY_F9 = 0x78;

    /**
     * Constant for the F10 function key.
     */
    public static final int AWT_KEY_F10 = 0x79;

    /**
     * Constant for the F11 function key.
     */
    public static final int AWT_KEY_F11 = 0x7A;

    /**
     * Constant for the F12 function key.
     */
    public static final int AWT_KEY_F12 = 0x7B;

    /**
     * Constant for the F13 function key.
     *
     * @since 1.2
     */
    /* F13 - F24 are used on IBM 3270 keyboard; use random range for constants. */
    public static final int AWT_KEY_F13 = 0xF000;

    /**
     * Constant for the F14 function key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_F14 = 0xF001;

    /**
     * Constant for the F15 function key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_F15 = 0xF002;

    /**
     * Constant for the F16 function key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_F16 = 0xF003;

    /**
     * Constant for the F17 function key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_F17 = 0xF004;

    /**
     * Constant for the F18 function key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_F18 = 0xF005;

    /**
     * Constant for the F19 function key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_F19 = 0xF006;

    /**
     * Constant for the F20 function key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_F20 = 0xF007;

    /**
     * Constant for the F21 function key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_F21 = 0xF008;

    /**
     * Constant for the F22 function key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_F22 = 0xF009;

    /**
     * Constant for the F23 function key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_F23 = 0xF00A;

    /**
     * Constant for the F24 function key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_F24 = 0xF00B;

    /**
     * Constant for the PRINTSCREEN key.
     */
    public static final int AWT_KEY_PRINTSCREEN = 0x9A;

    /**
     * Constant for the INSERT key.
     */
    public static final int AWT_KEY_INSERT = 0x9B;

    /**
     * Constant for the HELP key.
     */
    public static final int AWT_KEY_HELP = 0x9C;

    /**
     * Constant for the META key.
     */
    public static final int AWT_KEY_META = 0x9D;

    /**
     * Constant for the BACK_QUOTE  key.
     */
    public static final int AWT_KEY_BACK_QUOTE = 0xC0;

    /**
     * Constant for the QUOTE key.
     */
    public static final int AWT_KEY_QUOTE = 0xDE;

    /**
     * Constant for the numeric keypad <b>up</b> arrow key.
     *
     * @see #AWT_KEY_UP
     * @since 1.2
     */
    public static final int AWT_KEY_KP_UP = 0xE0;

    /**
     * Constant for the numeric keypad <b>down</b> arrow key.
     *
     * @see #AWT_KEY_DOWN
     * @since 1.2
     */
    public static final int AWT_KEY_KP_DOWN = 0xE1;

    /**
     * Constant for the numeric keypad <b>left</b> arrow key.
     *
     * @see #AWT_KEY_LEFT
     * @since 1.2
     */
    public static final int AWT_KEY_KP_LEFT = 0xE2;

    /**
     * Constant for the numeric keypad <b>right</b> arrow key.
     *
     * @see #AWT_KEY_RIGHT
     * @since 1.2
     */
    public static final int AWT_KEY_KP_RIGHT = 0xE3;

    /* For European keyboards */
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_GRAVE = 0x80;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_ACUTE = 0x81;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_CIRCUMFLEX = 0x82;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_TILDE = 0x83;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_MACRON = 0x84;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_BREVE = 0x85;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_ABOVEDOT = 0x86;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_DIAERESIS = 0x87;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_ABOVERING = 0x88;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_DOUBLEACUTE = 0x89;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_CARON = 0x8a;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_CEDILLA = 0x8b;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_OGONEK = 0x8c;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_IOTA = 0x8d;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_VOICED_SOUND = 0x8e;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_DEAD_SEMIVOICED_SOUND = 0x8f;

    /**
     * @since 1.2
     */
    public static final int AWT_KEY_AMPERSAND = 0x96;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_ASTERISK = 0x97;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_QUOTEDBL = 0x98;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_LESS = 0x99;

    /**
     * @since 1.2
     */
    public static final int AWT_KEY_GREATER = 0xa0;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_BRACELEFT = 0xa1;
    /**
     * @since 1.2
     */
    public static final int AWT_KEY_BRACERIGHT = 0xa2;

    /**
     * Constant for the "@" key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_AT = 0x0200;

    /**
     * Constant for the ":" key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_COLON = 0x0201;

    /**
     * Constant for the "^" key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_CIRCUMFLEX = 0x0202;

    /**
     * Constant for the "$" key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_DOLLAR = 0x0203;

    /**
     * Constant for the Euro currency sign key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_EURO_SIGN = 0x0204;

    /**
     * Constant for the "!" key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_EXCLAMATION_MARK = 0x0205;

    /**
     * Constant for the inverted exclamation mark key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_INVERTED_EXCLAMATION_MARK = 0x0206;

    /**
     * Constant for the "(" key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_LEFT_PARENTHESIS = 0x0207;

    /**
     * Constant for the "#" key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_NUMBER_SIGN = 0x0208;

    /**
     * Constant for the "+" key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_PLUS = 0x0209;

    /**
     * Constant for the ")" key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_RIGHT_PARENTHESIS = 0x020A;

    /**
     * Constant for the "_" key.
     *
     * @since 1.2
     */
    public static final int AWT_KEY_UNDERSCORE = 0x020B;

    /**
     * Constant for the Microsoft Windows "Windows" key.
     * It is used for both the left and right version of the key.
     *
     * @since 1.5
     */
    public static final int AWT_KEY_WINDOWS = 0x020C;
    static final HashMap<Integer, String> AWT_NAMES = new HashMap<>();
    static final HashMap<Integer, String> LWJGL_NAMES = new HashMap<>();

    static {
        // Use reflection to find out key names
        Field[] fields = KeyboardRemap.class.getFields();
        try {
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())
                        && Modifier.isPublic(field.getModifiers())
                        && Modifier.isFinal(field.getModifiers())
                        && field.getType().equals(int.class)
                        && field.getName().startsWith("AWT_")) { /* Don't use deprecated names */

                    int key = field.getInt(null);
                    String name = field.getName().substring(4);

                    AWT_NAMES.put(key, name);
                }

                if (Modifier.isStatic(field.getModifiers())
                        && Modifier.isPublic(field.getModifiers())
                        && Modifier.isFinal(field.getModifiers())
                        && field.getType().equals(int.class)
                        && field.getName().startsWith("LWJGL_")) { /* Don't use deprecated names */

                    int key = field.getInt(null);
                    String name = field.getName().substring(4);

                    LWJGL_NAMES.put(key, name);
                }

            }
        } catch (Exception ignored) {
        }

    }

    public static int remapFromAWT(int code) {
        switch (code) {
            case AWT_KEY_ENTER:
                return LWJGL_KEY_RETURN;
            case AWT_KEY_BACK_SPACE:
                return LWJGL_KEY_BACK;
            case AWT_KEY_TAB:
                return LWJGL_KEY_TAB;
//            case AWT_KEY_CANCEL:
//                return LWJGL_KEY_CANCEL;
            case AWT_KEY_CLEAR:
                return LWJGL_KEY_CLEAR;
            case AWT_KEY_SHIFT:
                return LWJGL_KEY_LSHIFT;
            case AWT_KEY_CONTROL:
                return LWJGL_KEY_LCONTROL;
            case AWT_KEY_ALT:
                return LWJGL_KEY_LMENU;
            case AWT_KEY_PAUSE:
                return LWJGL_KEY_PAUSE;
            case AWT_KEY_CAPS_LOCK:
                return LWJGL_KEY_CAPITAL;
            case AWT_KEY_ESCAPE:
                return LWJGL_KEY_ESCAPE;
            case AWT_KEY_SPACE:
                return LWJGL_KEY_SPACE;
            case AWT_KEY_PAGE_UP:
                return LWJGL_KEY_NEXT;
            case AWT_KEY_PAGE_DOWN:
                return LWJGL_KEY_PRIOR;
            case AWT_KEY_END:
                return LWJGL_KEY_END;
            case AWT_KEY_HOME:
                return LWJGL_KEY_HOME;
//            case AWT_KEY_KP_LEFT:
//                return LWJGL_KEY_KP_LEFT;
            case AWT_KEY_LEFT:
                return LWJGL_KEY_LEFT;
//            case AWT_KEY_KP_UP:
//                return LWJGL_KEY_KP_UP;
            case AWT_KEY_UP:
                return LWJGL_KEY_UP;
//            case AWT_KEY_KP_RIGHT:
//                return LWJGL_KEY_KP_RIGHT;
            case AWT_KEY_RIGHT:
                return LWJGL_KEY_RIGHT;
//            case AWT_KEY_KP_DOWN:
//                return LWJGL_KEY_KP_DOWN;
            case AWT_KEY_DOWN:
                return LWJGL_KEY_DOWN;
            case AWT_KEY_COMMA:
                return LWJGL_KEY_COMMA;
            case AWT_KEY_MINUS:
                return LWJGL_KEY_MINUS;
            case AWT_KEY_PERIOD:
                return LWJGL_KEY_PERIOD;
            case AWT_KEY_SLASH:
                return LWJGL_KEY_SLASH;
            case AWT_KEY_0:
                return LWJGL_KEY_0;
            case AWT_KEY_1:
                return LWJGL_KEY_1;
            case AWT_KEY_2:
                return LWJGL_KEY_2;
            case AWT_KEY_3:
                return LWJGL_KEY_3;
            case AWT_KEY_4:
                return LWJGL_KEY_4;
            case AWT_KEY_5:
                return LWJGL_KEY_5;
            case AWT_KEY_6:
                return LWJGL_KEY_6;
            case AWT_KEY_7:
                return LWJGL_KEY_7;
            case AWT_KEY_8:
                return LWJGL_KEY_8;
            case AWT_KEY_9:
                return LWJGL_KEY_9;
            case AWT_KEY_SEMICOLON:
                return LWJGL_KEY_SEMICOLON;
            case AWT_KEY_EQUALS:
                return LWJGL_KEY_EQUALS;
            case AWT_KEY_A:
                return LWJGL_KEY_A;
            case AWT_KEY_B:
                return LWJGL_KEY_B;
            case AWT_KEY_C:
                return LWJGL_KEY_C;
            case AWT_KEY_D:
                return LWJGL_KEY_D;
            case AWT_KEY_E:
                return LWJGL_KEY_E;
            case AWT_KEY_F:
                return LWJGL_KEY_F;
            case AWT_KEY_G:
                return LWJGL_KEY_G;
            case AWT_KEY_H:
                return LWJGL_KEY_H;
            case AWT_KEY_I:
                return LWJGL_KEY_I;
            case AWT_KEY_J:
                return LWJGL_KEY_J;
            case AWT_KEY_K:
                return LWJGL_KEY_K;
            case AWT_KEY_L:
                return LWJGL_KEY_L;
            case AWT_KEY_M:
                return LWJGL_KEY_M;
            case AWT_KEY_N:
                return LWJGL_KEY_N;
            case AWT_KEY_O:
                return LWJGL_KEY_O;
            case AWT_KEY_P:
                return LWJGL_KEY_P;
            case AWT_KEY_Q:
                return LWJGL_KEY_Q;
            case AWT_KEY_R:
                return LWJGL_KEY_R;
            case AWT_KEY_S:
                return LWJGL_KEY_S;
            case AWT_KEY_T:
                return LWJGL_KEY_T;
            case AWT_KEY_U:
                return LWJGL_KEY_U;
            case AWT_KEY_V:
                return LWJGL_KEY_V;
            case AWT_KEY_W:
                return LWJGL_KEY_W;
            case AWT_KEY_X:
                return LWJGL_KEY_X;
            case AWT_KEY_Y:
                return LWJGL_KEY_Y;
            case AWT_KEY_Z:
                return LWJGL_KEY_Z;
            case AWT_KEY_OPEN_BRACKET:
                return LWJGL_KEY_LBRACKET;
            case AWT_KEY_BACK_SLASH:
                return LWJGL_KEY_BACKSLASH;
            case AWT_KEY_CLOSE_BRACKET:
                return LWJGL_KEY_RBRACKET;
            case AWT_KEY_NUMPAD0:
                return LWJGL_KEY_NUMPAD0;
            case AWT_KEY_NUMPAD1:
                return LWJGL_KEY_NUMPAD1;
            case AWT_KEY_NUMPAD2:
                return LWJGL_KEY_NUMPAD2;
            case AWT_KEY_NUMPAD3:
                return LWJGL_KEY_NUMPAD3;
            case AWT_KEY_NUMPAD4:
                return LWJGL_KEY_NUMPAD4;
            case AWT_KEY_NUMPAD5:
                return LWJGL_KEY_NUMPAD5;
            case AWT_KEY_NUMPAD6:
                return LWJGL_KEY_NUMPAD6;
            case AWT_KEY_NUMPAD7:
                return LWJGL_KEY_NUMPAD7;
            case AWT_KEY_NUMPAD8:
                return LWJGL_KEY_NUMPAD8;
            case AWT_KEY_NUMPAD9:
                return LWJGL_KEY_NUMPAD9;
            case AWT_KEY_MULTIPLY:
                return LWJGL_KEY_MULTIPLY;
            case AWT_KEY_ADD:
                return LWJGL_KEY_ADD;
            case AWT_KEY_SEPARATOR:
                return LWJGL_KEY_DECIMAL;
            case AWT_KEY_SUBTRACT:
                return LWJGL_KEY_SUBTRACT;
            case AWT_KEY_DECIMAL:
                return LWJGL_KEY_DECIMAL;
            case AWT_KEY_DIVIDE:
                return LWJGL_KEY_DIVIDE;
            case AWT_KEY_DELETE:
                return LWJGL_KEY_DELETE;
            case AWT_KEY_NUM_LOCK:
                return LWJGL_KEY_NUMLOCK;
            case AWT_KEY_SCROLL_LOCK:
                return LWJGL_KEY_SCROLL;
            case AWT_KEY_F1:
                return LWJGL_KEY_F1;
            case AWT_KEY_F2:
                return LWJGL_KEY_F2;
            case AWT_KEY_F3:
                return LWJGL_KEY_F3;
            case AWT_KEY_F4:
                return LWJGL_KEY_F4;
            case AWT_KEY_F5:
                return LWJGL_KEY_F5;
            case AWT_KEY_F6:
                return LWJGL_KEY_F6;
            case AWT_KEY_F7:
                return LWJGL_KEY_F7;
            case AWT_KEY_F8:
                return LWJGL_KEY_F8;
            case AWT_KEY_F9:
                return LWJGL_KEY_F9;
            case AWT_KEY_F10:
                return LWJGL_KEY_F10;
            case AWT_KEY_F11:
                return LWJGL_KEY_F11;
            case AWT_KEY_F12:
                return LWJGL_KEY_F12;
            case AWT_KEY_F13:
                return LWJGL_KEY_F13;
            case AWT_KEY_F14:
                return LWJGL_KEY_F14;
            case AWT_KEY_F15:
                return LWJGL_KEY_F15;
            case AWT_KEY_F16:
                return LWJGL_KEY_F16;
            case AWT_KEY_F17:
                return LWJGL_KEY_F17;
            case AWT_KEY_F18:
                return LWJGL_KEY_F18;
            case AWT_KEY_F19:
                return LWJGL_KEY_F19;
//            case AWT_KEY_F20:
//                return LWJGL_KEY_F20;
//            case AWT_KEY_F21:
//                return LWJGL_KEY_F21;
//            case AWT_KEY_F22:
//                return LWJGL_KEY_F22;
//            case AWT_KEY_F23:
//                return LWJGL_KEY_F23;
//            case AWT_KEY_F24:
//                return LWJGL_KEY_F24;
            case AWT_KEY_PRINTSCREEN:
                return LWJGL_KEY_SYSRQ;
            case AWT_KEY_INSERT:
                return LWJGL_KEY_INSERT;
//            case AWT_KEY_HELP:
//                return LWJGL_KEY_HELP;
            case AWT_KEY_META:
                return LWJGL_KEY_LMETA;
//            case AWT_KEY_BACK_QUOTE:
//                return LWJGL_KEY_BACKQUOTE;
//            case AWT_KEY_QUOTE:
//                return LWJGL_KEY_QUOTE;
//            case AWT_KEY_DEAD_GRAVE:
//                return LWJGL_KEY_DEAD_GRAVE;
//            case AWT_KEY_DEAD_ACUTE:
//                return LWJGL_KEY_DEAD_ACUTE;
//            case AWT_KEY_DEAD_CIRCUMFLEX:
//                return LWJGL_KEY_DEAD_CIRCUMFLEX;
//            case AWT_KEY_DEAD_TILDE:
//                return LWJGL_KEY_DEAD_TILDE;
//            case AWT_KEY_DEAD_MACRON:
//                return LWJGL_KEY_DEAD_MACRON;
//            case AWT_KEY_DEAD_BREVE:
//                return LWJGL_KEY_DEAD_BREVE;
//            case AWT_KEY_DEAD_ABOVEDOT:
//                return LWJGL_KEY_DEAD_ABOVEDOT;
//            case AWT_KEY_DEAD_DIAERESIS:
//                return LWJGL_KEY_DEAD_DIAERESIS;
//            case AWT_KEY_DEAD_ABOVERING:
//                return LWJGL_KEY_DEAD_ABOVERING;
//            case AWT_KEY_DEAD_DOUBLEACUTE:
//                return LWJGL_KEY_DEAD_DOUBLEACUTE;
//            case AWT_KEY_DEAD_CARON:
//                return LWJGL_KEY_DEAD_CARON;
//            case AWT_KEY_DEAD_CEDILLA:
//                return LWJGL_KEY_DEAD_CEDILLA;
//            case AWT_KEY_DEAD_OGONEK:
//                return LWJGL_KEY_DEAD_OGONEK;
//            case AWT_KEY_DEAD_IOTA:
//                return LWJGL_KEY_DEAD_IOTA;
//            case AWT_KEY_DEAD_VOICED_SOUND:
//                return LWJGL_KEY_DEAD_VOICED_SOUND;
//            case AWT_KEY_DEAD_SEMIVOICED_SOUND:
//                return LWJGL_KEY_DEAD_SEMIVOICED_SOUND;
//            case AWT_KEY_AMPERSAND:
//                return LWJGL_KEY_AMPERSAND;
//            case AWT_KEY_ASTERISK:
//                return LWJGL_KEY_ASTERISK;
//            case AWT_KEY_QUOTEDBL:
//                return LWJGL_KEY_QUOTEDBL;
//            case AWT_KEY_LESS:
//                return LWJGL_KEY_LESS;
//            case AWT_KEY_GREATER:
//                return LWJGL_KEY_GREATER;
//            case AWT_KEY_BRACELEFT:
//                return LWJGL_KEY_BRACELEFT;
//            case AWT_KEY_BRACERIGHT:
//                return LWJGL_KEY_BRACERIGHT;
            case AWT_KEY_AT:
                return LWJGL_KEY_AT;
            case AWT_KEY_COLON:
                return LWJGL_KEY_COLON;
            case AWT_KEY_CIRCUMFLEX:
                return LWJGL_KEY_CIRCUMFLEX;
//            case AWT_KEY_DOLLAR:
//                return LWJGL_KEY_DOLLAR;
//            case AWT_KEY_EURO_SIGN:
//                return LWJGL_KEY_EURO_SIGN;
//            case AWT_KEY_EXCLAMATION_MARK:
//                return LWJGL_KEY_EXCLAMATION_MARK;
//            case AWT_KEY_INVERTED_EXCLAMATION_MARK:
//                return LWJGL_KEY_INVERTED_EXCLAMATION_MARK;
//            case AWT_KEY_LEFT_PARENTHESIS:
//                return LWJGL_KEY_LEFT_PARENTHESIS;
//            case AWT_KEY_NUMBER_SIGN:
//                return LWJGL_KEY_NUMBER_SIGN;
//            case AWT_KEY_PLUS:
//                return LWJGL_KEY_PLUS;
//            case AWT_KEY_RIGHT_PARENTHESIS:
//                return LWJGL_KEY_;
//            case AWT_KEY_UNDERSCORE:
//                return LWJGL_KEY_UNDERSCORE;
            case AWT_KEY_WINDOWS:
                return LWJGL_KEY_LMETA;
        }
        System.out.println("KeyboardRemap: Found unmapped key " + AWT_NAMES.getOrDefault(code, "<unknown>") + " (0x" + Integer.toHexString(code) + ")");
        return -1;
    }
}
