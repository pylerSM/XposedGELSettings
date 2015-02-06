package de.theknut.xposedgelsettings.hooks.appdrawer;

import android.graphics.Color;
import android.widget.TextView;

import de.robv.android.xposed.XC_MethodHook;
import de.theknut.xposedgelsettings.hooks.PreferencesHelper;

import static de.robv.android.xposed.XposedHelpers.callMethod;

public final class ApplyFromApplicationInfoHook extends XC_MethodHook {
	
	// http://androidxref.com/4.4.2_r1/xref/packages/apps/Launcher3/src/com/android/launcher3/PagedViewIcon.java#68
	// public void pviApplyFromApplicationInfo(AppInfo info, boolean scaleUp, PagedViewIcon.PressedCallback cb)
	
	@Override
	protected void afterHookedMethod(MethodHookParam param) throws Throwable {
		
		if (PreferencesHelper.hideIconLabelApps) {
			callMethod(param.thisObject, "setTextColor", Color.TRANSPARENT);
		}
		else {
			callMethod(param.thisObject, "setTextColor", PreferencesHelper.appdrawerIconLabelColor);

			if (!PreferencesHelper.appdrawerIconLabelShadow) {
				((TextView) param.thisObject).getPaint().clearShadowLayer();
			}
		}
	}
}