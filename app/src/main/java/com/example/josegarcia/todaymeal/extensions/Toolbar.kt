package com.example.josegarcia.todaymeal.extensions

import androidx.appcompat.widget.Toolbar

var Toolbar.arrowIcon
    get() = 0
    set(value) {
        setNavigationIcon(value)
    }