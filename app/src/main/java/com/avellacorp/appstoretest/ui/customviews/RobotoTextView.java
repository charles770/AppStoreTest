package com.avellacorp.appstoretest.ui.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.avellacorp.appstoretest.R;
import com.avellacorp.appstoretest.base.Constant;


public class RobotoTextView extends TextView {

    public RobotoTextView(Context context) {
        super(context);
        if (isInEditMode()) return;
        parseAttributes(null);
    }

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) return;
        parseAttributes(attrs);
    }

    public RobotoTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode()) return;
        parseAttributes(attrs);
    }

    public static Typeface getRoboto(Context context, int typeface) {
        switch (typeface) {
            case Constant.ROBOTO_BLACK:
                if (Roboto.sRobotoBlack == null) {
                    Roboto.sRobotoBlack = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Black.ttf");
                }
                return Roboto.sRobotoBlack;
            case Constant.ROBOTO_BLACK_ITALIC:
                if (Roboto.sRobotoBlackItalic == null) {
                    Roboto.sRobotoBlackItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-BlackItalic.ttf");
                }
                return Roboto.sRobotoBlackItalic;
            case Constant.ROBOTO_BOLD:
                if (Roboto.sRobotoBold == null) {
                    Roboto.sRobotoBold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
                }
                return Roboto.sRobotoBold;
            case Constant.ROBOTO_BOLD_CONDENSED:
                if (Roboto.sRobotoBoldCondensed == null) {
                    Roboto.sRobotoBoldCondensed = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-BoldCondensed.ttf");
                }
                return Roboto.sRobotoBoldCondensed;
            case Constant.ROBOTO_BOLD_CONDENSED_ITALIC:
                if (Roboto.sRobotoBoldCondensedItalic == null) {
                    Roboto.sRobotoBoldCondensedItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-BoldCondensedItalic.ttf");
                }
                return Roboto.sRobotoBoldCondensedItalic;
            case Constant.ROBOTO_BOLD_ITALIC:
                if (Roboto.sRobotoBoldItalic == null) {
                    Roboto.sRobotoBoldItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-BoldItalic.ttf");
                }
                return Roboto.sRobotoBoldItalic;
            case Constant.ROBOTO_CONDENSED:
                if (Roboto.sRobotoCondensed == null) {
                    Roboto.sRobotoCondensed = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Condensed.ttf");
                }
                return Roboto.sRobotoCondensed;
            case Constant.ROBOTO_CONDENSED_ITALIC:
                if (Roboto.sRobotoCondensedItalic == null) {
                    Roboto.sRobotoCondensedItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-CondensedItalic.ttf");
                }
                return Roboto.sRobotoCondensedItalic;
            case Constant.ROBOTO_ITALIC:
                if (Roboto.sRobotoItalic == null) {
                    Roboto.sRobotoItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Italic.ttf");
                }
                return Roboto.sRobotoItalic;
            case Constant.ROBOTO_LIGHT:
                if (Roboto.sRobotoLight == null) {
                    Roboto.sRobotoLight = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
                }
                return Roboto.sRobotoLight;
            case Constant.ROBOTO_LIGHT_ITALIC:
                if (Roboto.sRobotoLightItalic == null) {
                    Roboto.sRobotoLightItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-LightItalic.ttf");
                }
                return Roboto.sRobotoLightItalic;
            case Constant.ROBOTO_MEDIUM:
                if (Roboto.sRobotoMedium == null) {
                    Roboto.sRobotoMedium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
                }
                return Roboto.sRobotoMedium;
            case Constant.ROBOTO_MEDIUM_ITALIC:
                if (Roboto.sRobotoMediumItalic == null) {
                    Roboto.sRobotoMediumItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-MediumItalic.ttf");
                }
                return Roboto.sRobotoMediumItalic;
            default:
            case Constant.ROBOTO_REGULAR:
                if (Roboto.sRobotoRegular == null) {
                    Roboto.sRobotoRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
                }
                return Roboto.sRobotoRegular;
            case Constant.ROBOTO_THIN:
                if (Roboto.sRobotoThin == null) {
                    Roboto.sRobotoThin = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
                }
                return Roboto.sRobotoThin;
            case Constant.ROBOTO_THIN_ITALIC:
                if (Roboto.sRobotoThinItalic == null) {
                    Roboto.sRobotoThinItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-ThinItalic.ttf");
                }
                return Roboto.sRobotoThinItalic;
        }
    }

    private void parseAttributes(AttributeSet attrs) {
        int typeface;
        if (attrs == null) { //Not created from xml
            typeface = Constant.ROBOTO_REGULAR;
        } else {
            TypedArray values = getContext().obtainStyledAttributes(attrs, R.styleable.RobotoTextView);
            typeface = values.getInt(R.styleable.RobotoTextView_typeface, Constant.ROBOTO_REGULAR);
            values.recycle();
        }
        setTypeface(getRoboto(typeface));
    }

    public void setRobotoTypeface(int typeface) {
        setTypeface(getRoboto(typeface));
    }

    private Typeface getRoboto(int typeface) {
        return getRoboto(getContext(), typeface);
    }

    public static class Roboto {
        /* From attrs.xml file:
        <enum name="robotoBlack" value="0" />
        <enum name="robotoBlackItalic" value="1" />
        <enum name="robotoBold" value="2" />
        <enum name="robotoBoldItalic" value="3" />
        <enum name="robotoBoldCondensed" value="4" />
        <enum name="robotoBoldCondensedItalic" value="5" />
        <enum name="robotoCondensed" value="6" />
        <enum name="robotoCondensedItalic" value="7" />
        <enum name="robotoItalic" value="8" />
        <enum name="robotoLight" value="9" />
        <enum name="robotoLightItalic" value="10" />
        <enum name="robotoMedium" value="11" />
        <enum name="robotoMediumItalic" value="12" />
        <enum name="robotoRegular" value="13" />
        <enum name="robotoThin" value="14" />
        <enum name="robotoThinItalic" value="15" />
        */


        private static Typeface sRobotoBlack;
        private static Typeface sRobotoBlackItalic;
        private static Typeface sRobotoBold;
        private static Typeface sRobotoBoldItalic;
        private static Typeface sRobotoBoldCondensed;
        private static Typeface sRobotoBoldCondensedItalic;
        private static Typeface sRobotoCondensed;
        private static Typeface sRobotoCondensedItalic;
        private static Typeface sRobotoItalic;
        private static Typeface sRobotoLight;
        private static Typeface sRobotoLightItalic;
        private static Typeface sRobotoMedium;
        private static Typeface sRobotoMediumItalic;
        private static Typeface sRobotoRegular;
        private static Typeface sRobotoThin;
        private static Typeface sRobotoThinItalic;
    }
}