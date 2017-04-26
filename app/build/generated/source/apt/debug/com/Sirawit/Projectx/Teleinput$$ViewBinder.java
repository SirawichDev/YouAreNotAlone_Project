// Generated code from Butter Knife. Do not modify!
package com.Sirawit.Projectx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Teleinput$$ViewBinder<T extends com.Sirawit.Projectx.Teleinput> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624115, "field '_passwordText'");
    target._passwordText = finder.castView(view, 2131624115, "field '_passwordText'");
    view = finder.findRequiredView(source, 2131624112, "field '_loginButton'");
    target._loginButton = finder.castView(view, 2131624112, "field '_loginButton'");
    view = finder.findRequiredView(source, 2131624113, "field '_Cir'");
    target._Cir = finder.castView(view, 2131624113, "field '_Cir'");
  }

  @Override public void unbind(T target) {
    target._passwordText = null;
    target._loginButton = null;
    target._Cir = null;
  }
}
