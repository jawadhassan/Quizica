package com.example.hamid_pc.quizica;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Hamid-PC on 1/16/2017.
 */

public abstract class TextValidator implements TextWatcher {

    private final EditText editText;
    private String answerText;


    public TextValidator(EditText editText) {
        this.editText = editText;
    }

    public abstract void validate(EditText editText, String text);

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = editText.getText().toString();
        validate(editText, text);
    }


    public boolean textValidate(String text) {
        if ((text.length()) == 0 || text == null) {
            editText.setError("The field is required");
            return false;

        } else if (text.length() >= 400) {
            editText.setError("Maximum letters limit is 400");
            return false;
        } else if (text.length() <= 1) {
            editText.setError("Too Short");
            return false;
        }
        return true;
    }


    public boolean optionValidate(String text, String optionOne, String optionTwo, String optionThree) {
        if (text.equals(optionOne) || text.equals(optionTwo) || text.equals(optionThree)) {
            editText.setError("Duplicate Option");
            return false;
        }
        return true;
    }

    public boolean answerValidate(String text, String optionOne, String optionTwo, String optionThree, String optionFour) {



        if (!text.equals(optionOne) && !text.equals(optionTwo) && !text.equals(optionThree) && !text.equals(optionFour)) {
            editText.setError("Answer does not match with given options");
            return false;
        } else if (text == null) {

            return false;
        }
        return true;
    }


}