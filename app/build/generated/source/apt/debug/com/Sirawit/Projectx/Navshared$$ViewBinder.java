// Generated code from Butter Knife. Do not modify!
package com.Sirawit.Projectx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Navshared$$ViewBinder<T extends com.Sirawit.Projectx.Navshared> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624117, "field '_passwordText'");
    target._passwordText = finder.castView(view, 2131624117, "field '_passwordText'");
  }

  @Override public void unbind(T target) {
    target._passwordText = null;
  }
}
