// Generated code from Butter Knife. Do not modify!
package com.Sirawit.Projectx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.Sirawit.Projectx.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624100, "field '_loginButton'");
    target._loginButton = finder.castView(view, 2131624100, "field '_loginButton'");
  }

  @Override public void unbind(T target) {
    target._loginButton = null;
  }
}
