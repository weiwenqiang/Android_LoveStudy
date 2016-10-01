package com.example.lovestudy.view.custom;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

public class MaxLengthWatcher implements TextWatcher {

	private int maxLen = 0;
	private EditText editText = null;

	public MaxLengthWatcher(int maxlen, EditText editText) {
		this.maxLen = maxlen;
		this.editText = editText;
	}

	@Override
	public void afterTextChanged(Editable arg0) {

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		Editable editable = editText.getText();
		int len = editable.length();
		if (len > maxLen) {
			int selEndIndex = Selection.getSelectionEnd(editable);
			String string = editable.toString();
			String newString = string.substring(0, maxLen);
			editText.setText(newString);
			editable = editText.getText();
			int newlen = editable.length();
			if (selEndIndex > newlen) {
				selEndIndex = editable.length();
			}
			Selection.setSelection(editable, selEndIndex);
		}
	}

}
