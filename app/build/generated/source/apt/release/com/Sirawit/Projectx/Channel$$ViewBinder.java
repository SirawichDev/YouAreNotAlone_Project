// Generated code from Butter Knife. Do not modify!
package com.Sirawit.Projectx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Channel$$ViewBinder<T extends com.Sirawit.Projectx.Channel> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624090, "field 'tt'");
    target.tt = finder.castView(view, 2131624090, "field 'tt'");
  }

  @Override public void unbind(T target) {
    target.tt = null;
  }
}
