package br.com.moscatech.dumbpassword.utils

import br.com.moscatech.dumbpassword.databinding.IncludeAppBarBinding

fun IncludeAppBarBinding.setupAppBar(
    title: String,
    onBackClicked: () -> Unit = {},
) {
    tvAppBar.text = title
    vBack.setOnClickListener { onBackClicked.invoke() }
}