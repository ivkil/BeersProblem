package com.adevinta.beerproblem.presentation

import android.view.View

inline fun View.showIf(predicate: () -> Boolean): View {
    visibility = if (predicate()) View.VISIBLE else View.GONE
    return this
}
