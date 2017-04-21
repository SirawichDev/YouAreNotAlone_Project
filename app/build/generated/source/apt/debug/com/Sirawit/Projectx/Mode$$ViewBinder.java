// Generated code from Butter Knife. Do not modify!
package com.Sirawit.Projectx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Mode$$ViewBinder<T extends com.Sirawit.Projectx.Mode> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624111, "field 'bon'");
    target.bon = finder.castView(view, 2131624111, "field 'bon'");
    view = finder.findRequiredView(source, 2131624110, "field 'con'");
    target.con = finder.castView(view, 2131624110, "field 'con'");
  }

  @Override public void unbind(T target) {
    target.bon = null;
    target.con = null;
  }
}
